package kurlyk.view.task.computerSystemDiagramWindow;

import com.google.gson.Gson;
import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.Cursor;
import javafx.scene.ImageCursor;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Pair;
import kurlyk.common.PointGenerator;
import kurlyk.common.algorithm.graph.ComputerSystem.ComputerSystemElement;
import kurlyk.common.algorithm.graph.ComputerSystem.ComputerSystemElementType;
import kurlyk.common.algorithm.graph.GraphSystem;
import kurlyk.communication.Communicator;
import kurlyk.communication.UsverInfo;
import kurlyk.model.Question;
import kurlyk.transfer.ResultAnswerDto;
import kurlyk.transfer.answer.ComputerSystemAnswerDto;
import kurlyk.transfer.tasks.ComputerSystemDto;
import kurlyk.view.common.stage.StagePool;
import kurlyk.view.common.stage.Stages;
import kurlyk.view.components.DiagramContextMenu;
import kurlyk.view.components.MyHtmlEditor;
import kurlyk.view.components.fields.DoubleField;
import kurlyk.view.task.SubmitConfigurationController;
import kurlyk.view.task.TabPurpose;
import kurlyk.view.task.computerSystemDiagramWindow.characteristicWindow.CharacteristicStage;
import kurlyk.view.task.computerSystemDiagramWindow.computerSystemDiagram.ComputerSystemDiagramConnector;
import kurlyk.view.task.computerSystemDiagramWindow.computerSystemDiagram.ComputerSystemDiagramDetail;
import kurlyk.view.task.computerSystemDiagramWindow.computerSystemDiagram.ComputerSystemDiagramPictures;
import kurlyk.view.task.computerSystemDiagramWindow.computerSystemDiagram.DiagramElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashSet;
import java.util.Optional;
import java.util.function.Consumer;

@Component
@Scope("prototype")
public class ComputerSystemDiagramController extends SubmitConfigurationController<ComputerSystemDto> {

    @FXML private VBox root;
    @FXML private Button submit;
    @FXML private MyHtmlEditor textArea;

    @FXML private Button cpuButton;
    @FXML private Button ramButton;
    @FXML private Button ioButton;
    @FXML private Button pointButton;
    @FXML private Button connect;
    @FXML private AnchorPane drawPanel;
    @FXML private ScrollPane scrollPanel;
    @FXML private DoubleField availabilityFactorField;
    private Question question;

    @Autowired
    private Communicator communicator;

    @Autowired
    private StagePool stagePool;

    @Autowired
    private UsverInfo usverInfo;


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
                                availabilityFactorField.getValue())
                        );
                    }
                    break;
                case RAM:
                    if(event.getPickResult().getIntersectedNode().equals(drawPanel)) {
                        drawDetail(ComputerSystemDiagramDetail.createRamImage(event.getX(), event.getY(),
                                availabilityFactorField.getValue())
                        );
                    }
                    break;
                case IO:
                    if(event.getPickResult().getIntersectedNode().equals(drawPanel)) {
                        drawDetail(ComputerSystemDiagramDetail.createIoImage(event.getX(), event.getY(),
                                availabilityFactorField.getValue())
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
                        currentElement = ComputerSystemElementType.START_CONNECTION;
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
//        rebootDrawState();
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
//        rebootDrawState();
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
//                stagePool.getStage(Stages.COMPUTER_SYSTEM),
                new CharacteristicStage(computerSystemDiagramDetail.getComputerSystemElement())
        );
    }

    public void setQuestion(Question question, boolean editable, Consumer<Question> callbackActionBefore, Consumer<Question> callbackActionAfter, Stages stageForClose, TabPurpose tabPurpose) {
        this.question = question;
        ComputerSystemDto computerSystemDto = new Gson().fromJson(question.getAnswer(), ComputerSystemDto.class);
        submitConfiguration(
                editable,
                question,
                submit,
                communicator,
                stagePool,
                callbackActionBefore,
                callbackActionAfter,
                tabPurpose
        );
        setStageForClose(stageForClose);

        //Настройки работчего поля
        textArea.setDisable(!editable);
        textArea.setHtmlText(question.getQuestion());
        if(computerSystemDto != null){
            graphPainter(computerSystemDto.getGraphSystem());

        }
    }

    private void graphPainter(GraphSystem graphSystem){
        PointGenerator pointGenerator = new PointGenerator(300, 200);

        //Создаём набор элементов для рисования
        HashSet<ComputerSystemDiagramDetail> diagramDetails = new HashSet<>();
        for (ComputerSystemElement element : graphSystem.getElementSet()){
            Point2D point = pointGenerator.getRandomPoint();
            switch (element.getType()){
                case CPU:
                    diagramDetails.add(new ComputerSystemDiagramDetail(
                            element,
                            ComputerSystemDiagramPictures.CPU.getImage(),
                            point.getX(),
                            point.getY()
                    ));
                    break;
                case RAM:
                    diagramDetails.add(new ComputerSystemDiagramDetail(
                            element,
                            ComputerSystemDiagramPictures.RAM.getImage(),
                            point.getX(),
                            point.getY()
                    ));
                    break;
                case IO:
                    diagramDetails.add(new ComputerSystemDiagramDetail(
                            element,
                            ComputerSystemDiagramPictures.IO.getImage(),
                            point.getX(),
                            point.getY()
                    ));
                    break;
                case POINT:
                    diagramDetails.add(new ComputerSystemDiagramDetail(
                            element,
                            ComputerSystemDiagramPictures.POINT.getImage(),
                            point.getX(),
                            point.getY()
                    ));
                    break;
                case START_CONNECTION:
                case STOP_CONNECTION:
                case DEFAULT:
                    break;
            }
        }

        //Рисуем созданный набор
        for (ComputerSystemDiagramDetail diagramDetail : diagramDetails){
            drawDetail(diagramDetail);
        }

        //Ищем линии и рисуем их
        for (Pair<ComputerSystemElement, ComputerSystemElement> pair : graphSystem.getConnectionSet()){
            Optional<ComputerSystemDiagramDetail> diagramDetailFrom = diagramDetails
                    .stream()
                    .filter(diagramDetail -> diagramDetail.getComputerSystemElement().getUuid().equals(pair.getKey().getUuid()))
                    .findAny();
            Optional<ComputerSystemDiagramDetail> diagramDetailTo = diagramDetails
                    .stream()
                    .filter(diagramDetail -> diagramDetail.getComputerSystemElement().getUuid().equals(pair.getValue().getUuid()))
                    .findAny();
            if(diagramDetailFrom.isPresent() && diagramDetailTo.isPresent()){
                drawConnector(diagramDetailFrom.get(), diagramDetailTo.get());
            }
        }
    }

    @Override
    public ComputerSystemDto getResult() {
        return new ComputerSystemDto(graphSystem);
    }

    @Override
    public String getQuestionText() {
        return textArea.getHtmlText();
    }

    @Override
    public ResultAnswerDto getAnswerResult(Integer attempt) throws IOException {
        return communicator.testComputerSystemAnswer(
                ComputerSystemAnswerDto
                        .builder()
                        .entity(getResult())
                        .usverId(usverInfo.getTokenDto().getUsverId())
                        .questionId(question.getId())
                        .attemptsNumber(attempt)
                        .build()
        );
    }
}
