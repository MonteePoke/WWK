package kurlyk.view.task.computerSystemDiagramWindow;

import com.google.gson.Gson;
import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.Cursor;
import javafx.scene.ImageCursor;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import kurlyk.communication.Communicator;
import kurlyk.communication.UserProgress;
import kurlyk.graph.ComputerSystem.ComputerSystemElementType;
import kurlyk.graph.GraphSystem;
import kurlyk.transfer.TaskDto;
import kurlyk.transfer.tasks.ComputerSystemDto;
import kurlyk.view.common.component.DiagramContextMenu;
import kurlyk.view.common.component.NumberField;
import kurlyk.view.common.controller.Controller;
import kurlyk.view.common.controller.TaskBodyController;
import kurlyk.view.common.stage.StagePool;
import kurlyk.view.common.stage.Stages;
import kurlyk.view.createLabWindow.CreateLabSceneCreator;
import kurlyk.view.task.computerSystemDiagramWindow.characteristicWindow.CharacteristicStage;
import kurlyk.view.task.computerSystemDiagramWindow.computerSystemDiagram.ComputerSystemDiagramConnector;
import kurlyk.view.task.computerSystemDiagramWindow.computerSystemDiagram.ComputerSystemDiagramDetail;
import kurlyk.view.task.computerSystemDiagramWindow.computerSystemDiagram.ComputerSystemDiagramPictures;
import kurlyk.view.task.computerSystemDiagramWindow.computerSystemDiagram.DiagramElement;
import kurlyk.view.utils.FxDialogs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.function.Supplier;

@Component
@Scope("prototype")
public class ComputerSystemDiagramController extends Controller implements TaskBodyController<ComputerSystemDto> {

    @FXML private VBox root;
    @FXML private Button submit;
    @FXML private TextArea textArea;

    @FXML private Button cpuButton;
    @FXML private Button ramButton;
    @FXML private Button ioButton;
    @FXML private Button pointButton;
    @FXML private Button connect;
    @FXML private AnchorPane drawPanel;
    @FXML private ScrollPane scrollPanel;
    @FXML private NumberField availabilityFactorField;

    @Autowired
    private Communicator communicator;

    @Autowired
    private StagePool stagePool;

    @Autowired
    private UserProgress userProgress;

    private GraphSystem graphSystem = new GraphSystem(); //Граф
    private ComputerSystemElementType currentElement = ComputerSystemElementType.DEFAULT; //Тип элемента, который рисуется на текущий момент
    private ComputerSystemDiagramDetail startElementForConnection; //Точки начала и конца рисования линии
    private ComputerSystemDiagramDetail stopElementForConnection;

    public void initialize(){
        // Что бы Pane по размеру ScrollPane была, иначе она без элементов в минимальный размер уходит
        drawPanel.prefWidthProperty().bind(scrollPanel.widthProperty());
        drawPanel.prefHeightProperty().bind(scrollPanel.heightProperty());

        cpuButton.setOnAction(event -> {
            drawPanel.setCursor(new ImageCursor(ComputerSystemDiagramPictures.CPU.getImage()));
            currentElement = ComputerSystemElementType.CPU;
        });

        ramButton.setOnAction(event -> {
            drawPanel.setCursor(new ImageCursor(ComputerSystemDiagramPictures.RAM.getImage()));
            currentElement = ComputerSystemElementType.RAM;
        });

        ioButton.setOnAction(event -> {
            drawPanel.setCursor(new ImageCursor(ComputerSystemDiagramPictures.IO.getImage()));
            currentElement = ComputerSystemElementType.IO;
        });

        pointButton.setOnAction(event -> {
            drawPanel.setCursor(new ImageCursor(ComputerSystemDiagramPictures.POINT.getImage()));
            currentElement = ComputerSystemElementType.POINT;
        });

        connect.setOnAction(event -> {
            drawPanel.setCursor(Cursor.CROSSHAIR);
            currentElement = ComputerSystemElementType.START_CONNECTION;
        });

        drawPanel.setOnMouseClicked(event -> {
            if(MouseButton.SECONDARY == event.getButton()){     //Отмена на ПКМ
                rebootDrawState();
                return;
            }
            switch (currentElement){
                case CPU:
                    if(event.getPickResult().getIntersectedNode().equals(drawPanel)){
                        drawDetail(ComputerSystemDiagramDetail.createCpuImage(event.getX(), event.getY(),
                                availabilityFactorField.getNumber())
                        );
                    }
                    break;
                case RAM:
                    if(event.getPickResult().getIntersectedNode().equals(drawPanel)) {
                        drawDetail(ComputerSystemDiagramDetail.createRamImage(event.getX(), event.getY(),
                                availabilityFactorField.getNumber())
                        );
                    }
                    break;
                case IO:
                    if(event.getPickResult().getIntersectedNode().equals(drawPanel)) {
                        drawDetail(ComputerSystemDiagramDetail.createIoImage(event.getX(), event.getY(),
                                availabilityFactorField.getNumber())
                        );
                    }
                    break;
                case POINT:
                    if(event.getPickResult().getIntersectedNode().equals(drawPanel)) {
                        drawDetail(ComputerSystemDiagramDetail.createPointImage(event.getX(), event.getY()));
                    }
                    break;
                case START_CONNECTION:
                    if(event.getPickResult().getIntersectedNode().getParent() instanceof ComputerSystemDiagramDetail) {
                        startElementForConnection = (ComputerSystemDiagramDetail) event.getPickResult().getIntersectedNode().getParent();
                        currentElement = ComputerSystemElementType.STOP_CONNECTION;
                    }
                    break;
                case STOP_CONNECTION:
                    if(event.getPickResult().getIntersectedNode().getParent() instanceof ComputerSystemDiagramDetail) {
                        stopElementForConnection = (ComputerSystemDiagramDetail) event.getPickResult().getIntersectedNode().getParent();
                        currentElement = ComputerSystemElementType.DEFAULT;
                        drawConnector(startElementForConnection, stopElementForConnection);
                    }
                    break;
                case DEFAULT:
                    rebootDrawState();
                    break;
            }
        });
    }

    private void rebootDrawState(){
        drawPanel.setCursor(Cursor.DEFAULT);
        currentElement = ComputerSystemElementType.DEFAULT;
    }

    private void drawDetail(ComputerSystemDiagramDetail element){
        //Перемещение
        element.setOnMousePressed(event -> {
            Point2D point = new Point2D(event.getSceneX() - element.getLayoutX(), event.getSceneY() - element.getLayoutY());
            element.setPressurePoint(point);
        });
        element.setOnMouseDragged(event -> {
            element.setLayoutX(event.getSceneX() - element.getPressurePoint().getX());
            element.setLayoutY(event.getSceneY() - element.getPressurePoint().getY());
        });

        //Контекстное меню
        DiagramContextMenu diagramContextMenu = new DiagramContextMenu();
        //Рисовать коннектор
        diagramContextMenu.setConnectAction(() -> {
            drawPanel.setCursor(Cursor.CROSSHAIR);
            startElementForConnection = element;
            currentElement = ComputerSystemElementType.STOP_CONNECTION;
        });
        //Показать характеристики
        if (element.getComputerSystemElement().getType() != ComputerSystemElementType.POINT) {
            diagramContextMenu.setShowCharacteristicsAction(() -> showCharacteristics(element));
        }
        //Удалить
        diagramContextMenu.setDeleteAction(() -> deleteComputerSystemDiagramDetail(element));
        //Рисуем
        drawDiagramContextMenu(element, diagramContextMenu);

        //На работу
        graphSystem.add(element.getComputerSystemElement());
        drawPanel.getChildren().add(element);
        rebootDrawState();
    }

    private void drawConnector(ComputerSystemDiagramDetail elementFrom, ComputerSystemDiagramDetail elementTo){
        if(!(elementFrom.isConnected(elementTo) || elementFrom.equals(elementTo))){
            ComputerSystemDiagramConnector connector = new ComputerSystemDiagramConnector(elementFrom, elementTo);
            //Контекстное меню
            DiagramContextMenu diagramContextMenu = new DiagramContextMenu();
            //Удалить
            diagramContextMenu.setDeleteAction(() -> deleteComputerSystemDiagramConnector(connector));
            //Рисуем
            drawDiagramContextMenu(connector, diagramContextMenu);

            //Добавление в элементы коннекторов
            elementFrom.getConnectors().add(connector);
            elementTo.getConnectors().add(connector);

            //На работу
            graphSystem.connect(elementFrom.getComputerSystemElement(), elementTo.getComputerSystemElement());
            drawPanel.getChildren().add(connector);
            //Что бы линия была всегда сзади
            elementFrom.toFront();
            elementTo.toFront();
        }
        rebootDrawState();
    }

    private <T extends Node & DiagramElement> void drawDiagramContextMenu(T diagramElement, DiagramContextMenu diagramContextMenu){
        diagramElement.setOnContextMenuRequested(event -> {
            diagramContextMenu.show(diagramElement, event.getScreenX(), event.getScreenY());
        });
    }

    private void deleteComputerSystemDiagramDetail(ComputerSystemDiagramDetail detail){
        graphSystem.remove(detail.getComputerSystemElement());
        deleteDiagramElement(detail);
    }

    private void deleteComputerSystemDiagramConnector(ComputerSystemDiagramConnector connector){
        graphSystem.disconnect(connector.getElementFrom().getComputerSystemElement(),
                connector.getElementTo().getComputerSystemElement()
        );
        deleteDiagramElement(connector);
    }

    private void deleteDiagramElement(DiagramElement diagramElement){
        diagramElement.prepareForRemoval();
        diagramElement.getDiagramElementsForRemove().forEach(elementForRemove -> drawPanel.getChildren().remove(elementForRemove));
        drawPanel.getChildren().remove(diagramElement);
    }

    private void showCharacteristics(ComputerSystemDiagramDetail computerSystemDiagramDetail){
        stagePool.pushStageAndShowModal(Stages.CHARACTERISTIC,
                stagePool.getStage(Stages.COMPUTER_SYSTEM),
                new CharacteristicStage(computerSystemDiagramDetail.getComputerSystemElement())
        );
    }

    public void setQuestion(TaskDto taskDto, ComputerSystemDto computerSystemDto, boolean editable) {
        final ComputerSystemDto rightComputerSystemDto = computerSystemDto;
        commonConfiguration(taskDto, () -> isRightAnswer(rightComputerSystemDto), editable);
//        if (editable && formulaDto.getLatexFormula() != null) {
//            inputField.setText(formulaDto.getLatexFormula());
//        }
    }

    private void commonConfiguration(TaskDto taskDto, Supplier<Boolean> isRightAnswer, boolean editable) {
        textArea.setEditable(editable);
        if (editable){
            submit.setOnAction(event -> {
                taskDto.setQuestion(textArea.getText());
                taskDto.setAnswer(new Gson().toJson(getResult()));
                try {
                    communicator.postTask(taskDto);
                    stagePool.getStage(Stages.CREATE_LAB).setScene(new CreateLabSceneCreator().getScene());
                } catch (IOException e) {
                    FxDialogs.showError("", "Ошибка отправки данных");
                }
            });
        } else{
            textArea.setText(taskDto.getQuestion());
            submit.setOnAction(event -> {
                userProgress.getProgress().put(taskDto.getId(), isRightAnswer.get() ? 100 : 0);
                FxDialogs.showInformation("Результат", isRightAnswer.get() ? "Верно" : "Неверно");
            });
        }
    }

    public boolean isRightAnswer(ComputerSystemDto computerSystemDto){
        return computerSystemDto.getGraphSystem().isomorfic(getResult().getGraphSystem());
    }

    @Override
    public ComputerSystemDto getResult() {
        return new ComputerSystemDto(graphSystem);
    }
}
