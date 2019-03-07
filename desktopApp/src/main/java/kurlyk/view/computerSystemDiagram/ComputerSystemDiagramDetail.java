package kurlyk.view.computerSystemDiagram;


import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import kurlyk.graph.ComputerSystem.ComputerSystemElement;
import kurlyk.graph.ComputerSystem.ComputerSystemElementTypes;
import kurlyk.view.common.draw.DiagramElement;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@EqualsAndHashCode(exclude = {"pressurePoint", "connectors", "elements"}, callSuper = false)
public class ComputerSystemDiagramDetail extends ImageView implements DiagramElement {


    @Getter @Setter private Point2D pressurePoint;  //Точка нажатия на элементе
    @Getter private List<ComputerSystemDiagramConnector> connectors; //Все линии, которые подведены к этому элементу
    @Getter private List<ComputerSystemDiagramDetail> elements; //Все элементы, соединённые с этим
    @Getter private UUID uuid;
    @Getter private ComputerSystemElement computerSystemElement;

    public ComputerSystemDiagramDetail(ComputerSystemElement computerSystemElement, Image image, double x, double y) {
        super(image);
        this.connectors = new ArrayList<>();
        this.elements = new ArrayList<>();
        this.uuid = UUID.randomUUID();
        this.computerSystemElement = computerSystemElement;
        setX(x);
        setY(y);
    }

    public static ComputerSystemDiagramDetail createCpuImage(double x, double y, double availabilityFactor){
        System.out.println(availabilityFactor);
        return new ComputerSystemDiagramDetail(new ComputerSystemElement(ComputerSystemElementTypes.CPU, availabilityFactor),
                ComputerSystemPictures.CPU.getImage(),
                x,
                y
        );
    }

    public static ComputerSystemDiagramDetail createRamImage(double x, double y, double availabilityFactor){
        return new ComputerSystemDiagramDetail(new ComputerSystemElement(ComputerSystemElementTypes.CPU, availabilityFactor),
                ComputerSystemPictures.RAM.getImage(),
                x,
                y
        );
    }

    public static ComputerSystemDiagramDetail createIoImage(double x, double y, double availabilityFactor){
        return new ComputerSystemDiagramDetail(new ComputerSystemElement(ComputerSystemElementTypes.CPU, availabilityFactor),
                ComputerSystemPictures.IO.getImage(),
                x,
                y
        );
    }

    public static ComputerSystemDiagramDetail createPointImage(double x, double y, double availabilityFactor){
        return new ComputerSystemDiagramDetail(new ComputerSystemElement(ComputerSystemElementTypes.CPU, availabilityFactor),
                ComputerSystemPictures.POINT.getImage(),
                x,
                y
        );
    }

    @Override
    public List<? extends DiagramElement> getDiagramElementsForRemove() {
        return connectors;
    }

    public boolean isConnected(ComputerSystemDiagramDetail suspectedElement){
        return getElements()
                .stream()
                .anyMatch(element -> element.equals(suspectedElement));
    }
}
