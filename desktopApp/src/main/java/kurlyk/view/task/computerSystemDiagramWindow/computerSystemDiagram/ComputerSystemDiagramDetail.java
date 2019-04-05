package kurlyk.view.task.computerSystemDiagramWindow.computerSystemDiagram;


import javafx.beans.binding.Bindings;
import javafx.geometry.Point2D;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.StringConverter;
import javafx.util.converter.NumberStringConverter;
import kurlyk.graph.ComputerSystem.ComputerSystemElement;
import kurlyk.graph.ComputerSystem.ComputerSystemElementType;
import kurlyk.view.common.component.DiagramContextMenu;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;


@EqualsAndHashCode(exclude = {"diagramContextMenu", "pressurePoint", "connectors"}, callSuper = true)
public class ComputerSystemDiagramDetail extends ComputerSystemDiagramElement {


    @Getter private ImageView imageView; //Картинка
    @Getter @Setter private DiagramContextMenu diagramContextMenu; //Контекстное меню
    @Getter @Setter private Point2D pressurePoint;  //Точка нажатия на элементе
    @Getter private List<ComputerSystemDiagramConnector> connectors; //Все линии, которые подведены к этому элементу
    @Getter private ComputerSystemElement computerSystemElement; //Характеристики


    public ComputerSystemDiagramDetail(ComputerSystemElement computerSystemElement, Image image, double x, double y) {
        super(x, y);
        imageView = new ImageView(image);
        this.connectors = new ArrayList<>();
        this.computerSystemElement = computerSystemElement;
        getChildren().add(imageView);
        //Создание Лейбла
        if (computerSystemElement.getAvailabilityFactor() != null) {
            Label label = new Label();
            StringConverter<Number> converter = new NumberStringConverter();
            Bindings.bindBidirectional(label.textProperty(),
                    computerSystemElement.getAvailabilityFactor(),
                    converter
            );
            label.setStyle("-fx-font-weight: bold;");
            getChildren().add(label);
        }
    }

    public static ComputerSystemDiagramDetail createCpuImage(double x, double y, double availabilityFactor){
        return new ComputerSystemDiagramDetail(new ComputerSystemElement(ComputerSystemElementType.CPU, availabilityFactor),
                ComputerSystemDiagramPictures.CPU.getImage(),
                x,
                y
        );
    }

    public static ComputerSystemDiagramDetail createRamImage(double x, double y, double availabilityFactor){
        return new ComputerSystemDiagramDetail(new ComputerSystemElement(ComputerSystemElementType.RAM, availabilityFactor),
                ComputerSystemDiagramPictures.RAM.getImage(),
                x,
                y
        );
    }

    public static ComputerSystemDiagramDetail createIoImage(double x, double y, double availabilityFactor){
        return new ComputerSystemDiagramDetail(new ComputerSystemElement(ComputerSystemElementType.IO, availabilityFactor),
                ComputerSystemDiagramPictures.IO.getImage(),
                x,
                y
        );
    }

    public static ComputerSystemDiagramDetail createPointImage(double x, double y){
        return new ComputerSystemDiagramDetail(new ComputerSystemElement(ComputerSystemElementType.POINT, 1),
                ComputerSystemDiagramPictures.POINT.getImage(),
                x,
                y
        );
    }

    @Override
    public List<? extends DiagramElement> getDiagramElementsForRemove() {
        return connectors;
    }

    @Override
    public void prepareForRemoval() {

    }

    public boolean isConnected(ComputerSystemDiagramDetail suspectedElement){
        return getConnectors()
                .stream()
                .flatMap(connector -> Stream.of(connector.getElementFrom(), connector.getElementTo()))
                .anyMatch(element -> element.equals(suspectedElement));
    }
}
