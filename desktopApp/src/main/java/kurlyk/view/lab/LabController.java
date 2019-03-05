package kurlyk.view.lab;

import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.Cursor;
import javafx.scene.ImageCursor;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import kurlyk.graph.ComputerSystem.ComputerSystemElements;
import kurlyk.graph.ComputerSystem.ComputerSystemGraph;
import kurlyk.view.computerSystemDiagram.ComputerSystemPictures;
import kurlyk.view.fxCommon.Controller;
import org.springframework.stereotype.Component;

@Component
public class LabController extends Controller {

    @FXML private Button cpuButton;
    @FXML private Button ramButton;
    @FXML private Button ioButton;
    @FXML private Button pointButton;
    @FXML private Button connector;
    @FXML private Button cancel;
    @FXML private AnchorPane drawPanel;

    private ComputerSystemElements currentElement;
    private ComputerSystemGraph graph;
    private Point2D startPointForConnectionLine;
    private Point2D stopPointForConnectionLine;

    public void initialize(){
        cpuButton.setOnAction(event -> {
            drawPanel.setCursor(new ImageCursor(ComputerSystemPictures.CPU.getImage()));
            currentElement = ComputerSystemElements.CPU;

        });

        ramButton.setOnAction(event -> {
            drawPanel.setCursor(new ImageCursor(ComputerSystemPictures.RAM.getImage()));
            currentElement = ComputerSystemElements.RAM;
        });

        ioButton.setOnAction(event -> {
            drawPanel.setCursor(new ImageCursor(ComputerSystemPictures.IO.getImage()));
            currentElement = ComputerSystemElements.IO;
        });

        pointButton.setOnAction(event -> {
            drawPanel.setCursor(new ImageCursor(ComputerSystemPictures.POINT.getImage()));
            currentElement = ComputerSystemElements.POINT;
        });

        connector.setOnAction(event -> {
            drawPanel.setCursor(Cursor.DEFAULT);
            currentElement = ComputerSystemElements.START_CONNECTION;
        });

        cancel.setOnAction(event -> {
            rebootDrawPanel();
        });

        drawPanel.setOnMouseClicked(event -> {
            switch (currentElement){
                case CPU:
                    drawElement(new ImageView(ComputerSystemPictures.CPU.getImage()), event.getX(), event.getY());
                    break;
                case RAM:
                    drawElement(new ImageView(ComputerSystemPictures.RAM.getImage()), event.getX(), event.getY());
                    break;
                case IO:
                    drawElement(new ImageView(ComputerSystemPictures.IO.getImage()), event.getX(), event.getY());
                    break;
                case POINT:
                    drawElement(new ImageView(ComputerSystemPictures.POINT.getImage()), event.getX(), event.getY());
                    break;
                case START_CONNECTION:
                    startPointForConnectionLine = new Point2D(event.getX(), event.getY());
                    currentElement = ComputerSystemElements.STOP_CONNECTION;
                    break;
                case STOP_CONNECTION:
                    stopPointForConnectionLine = new Point2D(event.getX(), event.getY());
                    currentElement = ComputerSystemElements.DEFAULT;
                    break;
                case DEFAULT:
                    rebootDrawPanel();
                    break;

            }
        });
    }

    private void rebootDrawPanel(){
        drawPanel.setCursor(Cursor.DEFAULT);
        currentElement = ComputerSystemElements.DEFAULT;
    }

    private void drawElement(ImageView imageView, double x, double y){
        imageView.setX(x);
        imageView.setY(y);
        drawPanel.getChildren().add(imageView);
        rebootDrawPanel();
    }
}
