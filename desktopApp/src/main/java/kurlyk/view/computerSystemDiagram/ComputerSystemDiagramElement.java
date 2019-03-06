package kurlyk.view.computerSystemDiagram;


import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import kurlyk.graph.ComputerSystem.ComputerSystemElementTypes;
import kurlyk.view.common.draw.DiagramElement;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@EqualsAndHashCode(exclude = {"pressurePoint", "connectors", "elements"}, callSuper = false)
public class ComputerSystemDiagramElement extends ImageView implements DiagramElement {

    //Точка нажатия на элементе
    private Point2D pressurePoint;
    //Все линии, которые подведены к этому элементу
    private List<ComputerSystemDiagramConnector> connectors;
    //Все элементы, соединённые с этим
    private List<ComputerSystemDiagramElement> elements;
    private UUID uuid;
    private ComputerSystemElementTypes type;

    public ComputerSystemDiagramElement(ComputerSystemElementTypes type, Image image, double x, double y) {
        super(image);
        this.connectors = new ArrayList<>();
        this.elements = new ArrayList<>();
        this.uuid = UUID.randomUUID();
        this.type = type;
        setX(x);
        setY(y);
    }

    public static ComputerSystemDiagramElement createCpuImage(double x, double y){
        return new ComputerSystemDiagramElement(ComputerSystemElementTypes.CPU, ComputerSystemPictures.CPU.getImage(), x, y);
    }

    public static ComputerSystemDiagramElement createRamImage(double x, double y){
        return new ComputerSystemDiagramElement(ComputerSystemElementTypes.RAM, ComputerSystemPictures.RAM.getImage(), x, y);
    }

    public static ComputerSystemDiagramElement createIoImage(double x, double y){
        return new ComputerSystemDiagramElement(ComputerSystemElementTypes.IO, ComputerSystemPictures.IO.getImage(), x, y);
    }

    public static ComputerSystemDiagramElement createPointImage(double x, double y){
        return new ComputerSystemDiagramElement(ComputerSystemElementTypes.POINT, ComputerSystemPictures.POINT.getImage(), x, y);
    }

    public Point2D getPressurePoint() {
        return pressurePoint;
    }

    public void setPressurePoint(Point2D pressurePoint) {
        this.pressurePoint = pressurePoint;
    }

    public List<ComputerSystemDiagramConnector> getConnectors() {
        return connectors;
    }

    public List<ComputerSystemDiagramElement> getElements() {
        return elements;
    }

    @Override
    public UUID getUuid() {
        return uuid;
    }

    public ComputerSystemElementTypes getType() {
        return type;
    }

    public boolean isConnected(ComputerSystemDiagramElement suspectedElement){
        return getElements()
                .stream()
                .anyMatch(element -> element.equals(suspectedElement));
    }
}
