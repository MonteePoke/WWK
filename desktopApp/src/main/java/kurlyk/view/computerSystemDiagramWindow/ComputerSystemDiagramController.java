package kurlyk.view.computerSystemDiagramWindow;

import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.Cursor;
import javafx.scene.ImageCursor;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import kurlyk.graph.ComputerSystem.ComputerSystemElement;
import kurlyk.graph.ComputerSystem.ComputerSystemElementType;
import kurlyk.graph.GraphSystem;
import kurlyk.view.common.component.DiagramContextMenu;
import kurlyk.view.common.component.OnlyDoubleTextField;
import kurlyk.view.common.controller.Controller;
import kurlyk.view.common.controller.TaskBodyController;
import kurlyk.view.common.stage.StagePool;
import kurlyk.view.common.stage.Stages;
import kurlyk.view.computerSystemDiagramWindow.characteristicWindow.CharacteristicStage;
import kurlyk.view.computerSystemDiagramWindow.computerSystemDiagram.ComputerSystemDiagramConnector;
import kurlyk.view.computerSystemDiagramWindow.computerSystemDiagram.ComputerSystemDiagramDetail;
import kurlyk.view.computerSystemDiagramWindow.computerSystemDiagram.ComputerSystemDiagramPictures;
import kurlyk.view.computerSystemDiagramWindow.computerSystemDiagram.DiagramElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class ComputerSystemDiagramController
        extends Controller
        implements TaskBodyController<GraphSystem<ComputerSystemElement>> {

    @FXML private Button cpuButton;
    @FXML private Button ramButton;
    @FXML private Button ioButton;
    @FXML private Button pointButton;
    @FXML private Button connect;
    @FXML private AnchorPane drawPanel;
    @FXML private ScrollPane scrollPanel;
    @FXML private OnlyDoubleTextField availabilityFactorField;

    @Autowired
    private StagePool stagePool;

    private GraphSystem<ComputerSystemElement> graphSystem = new GraphSystem<>(); //Граф
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

            //Это тест
//            GraphSystem<ComputerSystemElement> graphAnother = new GraphSystem<>();
//            ComputerSystemElement elementAnother1 = new ComputerSystemElement(ComputerSystemElementType.CPU, 0.1d);
//            ComputerSystemElement elementAnother2 = new ComputerSystemElement(ComputerSystemElementType.CPU, 0d);
//            ComputerSystemElement elementAnother3 = new ComputerSystemElement(ComputerSystemElementType.POINT);
//            ComputerSystemElement elementAnother4 = new ComputerSystemElement(ComputerSystemElementType.RAM, 0d);
//            ComputerSystemElement elementAnother5 = new ComputerSystemElement(ComputerSystemElementType.RAM, 0d);
//            ComputerSystemElement elementAnother6 = new ComputerSystemElement(ComputerSystemElementType.POINT);
//            ComputerSystemElement elementAnother7 = new ComputerSystemElement(ComputerSystemElementType.IO, 0d);
//            ComputerSystemElement elementAnother8 = new ComputerSystemElement(ComputerSystemElementType.CPU, 0d);
//            graphAnother.add(elementAnother1);
//            graphAnother.add(elementAnother2);
//            graphAnother.add(elementAnother3);
//            graphAnother.add(elementAnother4);
//            graphAnother.add(elementAnother5);
//            graphAnother.add(elementAnother6);
//            graphAnother.add(elementAnother7);
//            graphAnother.add(elementAnother8);
//            graphAnother.connect(elementAnother1, elementAnother2);
//            graphAnother.connect(elementAnother2, elementAnother3);
//            graphAnother.connect(elementAnother3, elementAnother4);
//            graphAnother.connect(elementAnother3, elementAnother5);
//            graphAnother.connect(elementAnother4, elementAnother6);
//            graphAnother.connect(elementAnother5, elementAnother6);
//            graphAnother.connect(elementAnother6, elementAnother7);
//            System.out.println(graphSystem.isomorfic(graphAnother));

            if(MouseButton.SECONDARY == event.getButton()){     //Отмена на ПКМ
                rebootDrawState();
                return;
            }
            switch (currentElement){
                case CPU:
                    if(event.getPickResult().getIntersectedNode().equals(drawPanel)){
                        drawDetail(ComputerSystemDiagramDetail.createCpuImage(event.getX(), event.getY(),
                                availabilityFactorField.getDouble())
                        );
                    }
                    break;
                case RAM:
                    if(event.getPickResult().getIntersectedNode().equals(drawPanel)) {
                        drawDetail(ComputerSystemDiagramDetail.createRamImage(event.getX(), event.getY(),
                                availabilityFactorField.getDouble())
                        );
                    }
                    break;
                case IO:
                    if(event.getPickResult().getIntersectedNode().equals(drawPanel)) {
                        drawDetail(ComputerSystemDiagramDetail.createIoImage(event.getX(), event.getY(),
                                availabilityFactorField.getDouble())
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

    @Override
    public GraphSystem<ComputerSystemElement> getResult() {
        return graphSystem;
    }
}
