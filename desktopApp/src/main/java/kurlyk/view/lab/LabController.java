package kurlyk.view.lab;

import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.Cursor;
import javafx.scene.ImageCursor;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import kurlyk.graph.ComputerSystem.ComputerSystemElementTypes;
import kurlyk.graph.ComputerSystem.ComputerSystemGraph;
import kurlyk.view.common.Controller;
import kurlyk.view.computerSystemDiagram.ComputerSystemDiagramConnector;
import kurlyk.view.computerSystemDiagram.ComputerSystemDiagramElement;
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

    private ComputerSystemElementTypes currentElement;
    private ComputerSystemGraph graph;
    private ComputerSystemDiagramElement startElementForConnection;
    private ComputerSystemDiagramElement stopElementForConnection;

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
                        drawImage(ComputerSystemDiagramElement.createCpuImage(event.getX(), event.getY()));
                    }
                    break;
                case RAM:
                    if(event.getPickResult().getIntersectedNode().equals(drawPanel)) {
                        drawImage(ComputerSystemDiagramElement.createRamImage(event.getX(), event.getY()));
                    }
                    break;
                case IO:
                    if(event.getPickResult().getIntersectedNode().equals(drawPanel)) {
                        drawImage(ComputerSystemDiagramElement.createIoImage(event.getX(), event.getY()));
                    }
                    break;
                case POINT:
                    if(event.getPickResult().getIntersectedNode().equals(drawPanel)) {
                        drawImage(ComputerSystemDiagramElement.createPointImage(event.getX(), event.getY()));
                    }
                    break;
                case START_CONNECTION:
                    if(event.getPickResult().getIntersectedNode() instanceof ComputerSystemDiagramElement) {
                        startElementForConnection = (ComputerSystemDiagramElement) event.getPickResult().getIntersectedNode();
                        currentElement = ComputerSystemElementTypes.STOP_CONNECTION;
                    }
                    break;
                case STOP_CONNECTION:
                    if(event.getPickResult().getIntersectedNode() instanceof ComputerSystemDiagramElement) {
                        stopElementForConnection = (ComputerSystemDiagramElement) event.getPickResult().getIntersectedNode();
                        currentElement = ComputerSystemElementTypes.DEFAULT;
                        drawLine(startElementForConnection, stopElementForConnection);
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

    private void drawImage(ComputerSystemDiagramElement element){
        //Перемещение
        element.setOnMousePressed(event -> {
            Point2D point = new Point2D(event.getX() - element.getX(), event.getY() - element.getY());
            element.setPressurePoint(point);
        });
        element.setOnMouseDragged(event -> {
            element.setX(event.getX() - element.getPressurePoint().getX());
            element.setY(event.getY() - element.getPressurePoint().getY());
        });
        //Меню и рисование линий
        element.setOnMouseClicked(event -> {
            if(MouseButton.SECONDARY == event.getButton()){         //Удаление на ПКМ
                deleteElement(element);
            }
            else if(MouseButton.PRIMARY == event.getButton()){    //Рисование линии
//                if(currentElement == ComputerSystemElementTypes.START_CONNECTION){
//                    //Конец рисования линии
//                    stopPointForConnectionLine = new Point2D(event.getX(), event.getY());
//                    currentElement = ComputerSystemElementTypes.DEFAULT;
//                    drawLine(startPointForConnectionLine, stopPointForConnectionLine);
//                }else {
//                    //Начало рисования линии
//                    startPointForConnectionLine = new Point2D(event.getX(), event.getY());
//                    drawPanel.setCursor(new ImageCursor(ComputerSystemPictures.CONNECTOR.getImage()));
//                    currentElement = ComputerSystemElementTypes.START_CONNECTION;
//                }
            }
        });
        drawPanel.getChildren().add(element);
        rebootDrawState();
    }

    private void drawLine(ComputerSystemDiagramElement elementFrom, ComputerSystemDiagramElement elementTo){
        if(!(elementFrom.isConnected(elementTo) || elementFrom.equals(elementTo))){
            ComputerSystemDiagramConnector connector = new ComputerSystemDiagramConnector(elementFrom, elementTo);
            connector.setOnMouseClicked(event -> {
                if(MouseButton.SECONDARY == event.getButton()){
                    drawPanel.getChildren().remove(connector);
                    return;
                }

            });
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

    private void deleteElement(ComputerSystemDiagramElement element){
        for(ComputerSystemDiagramConnector connector : element.getConnectors()){
            drawPanel.getChildren().remove(connector);
        }
        drawPanel.getChildren().remove(element);
    }
}
