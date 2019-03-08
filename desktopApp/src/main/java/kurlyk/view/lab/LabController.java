package kurlyk.view.lab;

import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.Cursor;
import javafx.scene.ImageCursor;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import kurlyk.graph.ComputerSystem.ComputerSystemElementTypes;
import kurlyk.graph.ComputerSystem.ComputerSystemGraph;
import kurlyk.view.common.Controller;
import kurlyk.view.common.component.OnlyDoubleTextField;
import kurlyk.view.common.draw.DiagramContextMenu;
import kurlyk.view.common.draw.DiagramElement;
import kurlyk.view.computerSystemDiagram.ComputerSystemDiagramConnector;
import kurlyk.view.computerSystemDiagram.ComputerSystemDiagramDetail;
import kurlyk.view.computerSystemDiagram.ComputerSystemPictures;
import org.springframework.stereotype.Component;

@Component
public class LabController extends Controller {

    @FXML private Button cpuButton;
    @FXML private Button ramButton;
    @FXML private Button ioButton;
    @FXML private Button pointButton;
    @FXML private Button connect;
    @FXML private AnchorPane drawPanel;
    @FXML private ScrollPane scrollPanel;
    @FXML private OnlyDoubleTextField availabilityFactorField;


    private ComputerSystemGraph graph; //Граф
    private ComputerSystemElementTypes currentElement; //Тип элемента, который рисуется на текущий момент
    private ComputerSystemDiagramDetail startElementForConnection; //Точки начала и конца рисования линии
    private ComputerSystemDiagramDetail stopElementForConnection;

    public void initialize(){
        // Что бы AnchorPane по размеру ScrollPane была, иначе она без элементов в минимальный размер уходит
        drawPanel.prefWidthProperty().bind(scrollPanel.widthProperty());
        drawPanel.prefHeightProperty().bind(scrollPanel.heightProperty());

        cpuButton.setOnAction(event -> {
            drawPanel.setCursor(new ImageCursor(ComputerSystemPictures.CPU.getImage()));
            currentElement = ComputerSystemElementTypes.CPU;
        });

        ramButton.setOnAction(event -> {
            drawPanel.setCursor(new ImageCursor(ComputerSystemPictures.RAM.getImage()));
            currentElement = ComputerSystemElementTypes.RAM;
        });

        ioButton.setOnAction(event -> {
            drawPanel.setCursor(new ImageCursor(ComputerSystemPictures.IO.getImage()));
            currentElement = ComputerSystemElementTypes.IO;
        });

        pointButton.setOnAction(event -> {
            drawPanel.setCursor(new ImageCursor(ComputerSystemPictures.POINT.getImage()));
            currentElement = ComputerSystemElementTypes.POINT;
        });

        connect.setOnAction(event -> {
            drawPanel.setCursor(Cursor.CROSSHAIR);
            currentElement = ComputerSystemElementTypes.START_CONNECTION;
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
                        drawDetail(ComputerSystemDiagramDetail.createPointImage(event.getX(), event.getY(),
                                availabilityFactorField.getDouble())
                        );
                    }
                    break;
                case START_CONNECTION:
                    if(event.getPickResult().getIntersectedNode() instanceof ComputerSystemDiagramDetail) {
                        startElementForConnection = (ComputerSystemDiagramDetail) event.getPickResult().getIntersectedNode();
                        currentElement = ComputerSystemElementTypes.STOP_CONNECTION;
                    }
                    break;
                case STOP_CONNECTION:
                    if(event.getPickResult().getIntersectedNode() instanceof ComputerSystemDiagramDetail) {
                        stopElementForConnection = (ComputerSystemDiagramDetail) event.getPickResult().getIntersectedNode();
                        currentElement = ComputerSystemElementTypes.DEFAULT;
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
        currentElement = ComputerSystemElementTypes.DEFAULT;
    }

    private void drawDetail(ComputerSystemDiagramDetail element){
        //Перемещение
        element.setOnMousePressed(event -> {
            Point2D point = new Point2D(event.getX() - element.getX(), event.getY() - element.getY());
            element.setPressurePoint(point);
        });
        element.setOnMouseDragged(event -> {
            element.setX(event.getX() - element.getPressurePoint().getX());
            element.setY(event.getY() - element.getPressurePoint().getY());
        });
        //Контекстное меню
        drawDiagramContextMenu(element);
        drawPanel.getChildren().add(element);
        rebootDrawState();
    }

    private <T extends Node & DiagramElement> void drawDiagramContextMenu(T diagramElement){
        DiagramContextMenu diagramContextMenu = new DiagramContextMenu();
        diagramContextMenu.setDeleteAction(() -> deleteDiagramElement(diagramElement));

        diagramElement.setOnContextMenuRequested(event -> {
            diagramContextMenu.show(diagramElement, event.getScreenX(), event.getScreenY());
        });
    }

    private void drawConnector(ComputerSystemDiagramDetail elementFrom, ComputerSystemDiagramDetail elementTo){
        if(!(elementFrom.isConnected(elementTo) || elementFrom.equals(elementTo))){
            ComputerSystemDiagramConnector connector = new ComputerSystemDiagramConnector(elementFrom, elementTo);
            drawPanel.getChildren().add(connector);
            elementFrom.getConnectors().add(connector);
            elementTo.getConnectors().add(connector);
            elementFrom.getElements().add(elementTo);
            elementTo.getElements().add(elementFrom);
            elementFrom.toFront();
            elementTo.toFront();
        }
        rebootDrawState();
    }

    private void deleteDiagramElement(DiagramElement diagramElement){
        for(DiagramElement diagramElementForRemove : diagramElement.getDiagramElementsForRemove()){
            drawPanel.getChildren().remove(diagramElementForRemove);
        }
        drawPanel.getChildren().remove(diagramElement);
    }
}
