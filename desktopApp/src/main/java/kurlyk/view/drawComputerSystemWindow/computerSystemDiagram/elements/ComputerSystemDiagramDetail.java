package kurlyk.view.drawComputerSystemWindow.computerSystemDiagram.elements;


import javafx.geometry.Point2D;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import kurlyk.graph.ComputerSystem.ComputerSystemElement;
import kurlyk.graph.ComputerSystem.ComputerSystemElementTypes;
import kurlyk.view.common.component.DiagramContextMenu;
import kurlyk.view.drawComputerSystemWindow.computerSystemDiagram.ComputerSystemDiagramElement;
import kurlyk.view.drawComputerSystemWindow.computerSystemDiagram.ComputerSystemPictures;
import kurlyk.view.drawComputerSystemWindow.computerSystemDiagram.DiagramElement;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@EqualsAndHashCode(exclude = {"pressurePoint", "connectors", "elements"}, callSuper = true)
public class ComputerSystemDiagramDetail extends ComputerSystemDiagramElement {


    @Getter @Setter private Point2D pressurePoint;  //Точка нажатия на элементе
    @Getter private List<ComputerSystemDiagramConnector> connectors; //Все линии, которые подведены к этому элементу
    @Getter private List<ComputerSystemDiagramDetail> elements; //Все элементы, соединённые с этим
    @Getter private ComputerSystemElement computerSystemElement; //Элемент с характеристиками из самого графа
    @Getter @Setter private DiagramContextMenu diagramContextMenu; //Контекстное меню
    @Getter private ImageView imageView; //Картинка

    public ComputerSystemDiagramDetail(ComputerSystemElement computerSystemElement, Image image, double x, double y) {
        super(x, y);
        imageView = new ImageView(image);
        this.connectors = new ArrayList<>();
        this.elements = new ArrayList<>();
        this.computerSystemElement = computerSystemElement;
        getChildren().add(imageView);
        //Создание Лейбла
        if (computerSystemElement.getAvailabilityFactor() != null) {
            Label label = new Label(computerSystemElement.getAvailabilityFactor().toString());
            label.setStyle("-fx-font-weight: bold;");
            getChildren().add(label);
        }
    }

    public static ComputerSystemDiagramDetail createCpuImage(double x, double y, double availabilityFactor){
        return new ComputerSystemDiagramDetail(new ComputerSystemElement(ComputerSystemElementTypes.CPU, availabilityFactor),
                ComputerSystemPictures.CPU.getImage(),
                x,
                y
        );
    }

    public static ComputerSystemDiagramDetail createRamImage(double x, double y, double availabilityFactor){
        return new ComputerSystemDiagramDetail(new ComputerSystemElement(ComputerSystemElementTypes.RAM, availabilityFactor),
                ComputerSystemPictures.RAM.getImage(),
                x,
                y
        );
    }

    public static ComputerSystemDiagramDetail createIoImage(double x, double y, double availabilityFactor){
        return new ComputerSystemDiagramDetail(new ComputerSystemElement(ComputerSystemElementTypes.IO, availabilityFactor),
                ComputerSystemPictures.IO.getImage(),
                x,
                y
        );
    }

    public static ComputerSystemDiagramDetail createPointImage(double x, double y){
        return new ComputerSystemDiagramDetail(new ComputerSystemElement(ComputerSystemElementTypes.POINT),
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
