package kurlyk.view.drawComputerSystemWindow.computerSystemDiagram;


import javafx.beans.binding.Bindings;
import javafx.geometry.Point2D;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.StringConverter;
import javafx.util.converter.NumberStringConverter;
import kurlyk.graph.ComputerSystem.ComputerSystemElementType;
import kurlyk.view.common.component.DiagramContextMenu;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@EqualsAndHashCode(exclude = {"pressurePoint", "connectors", "elements", "computerSystemElementProperty"}, callSuper = true)
public class ComputerSystemDiagramDetail extends ComputerSystemDiagramElement {


    @Getter @Setter private Point2D pressurePoint;  //Точка нажатия на элементе
    @Getter private List<ComputerSystemDiagramConnector> connectors; //Все линии, которые подведены к этому элементу
    @Getter private List<ComputerSystemDiagramDetail> elements; //Все элементы, соединённые с этим
    @Getter private ComputerSystemElementProperty computerSystemElementProperty; //Характеристики
    @Getter @Setter private DiagramContextMenu diagramContextMenu; //Контекстное меню
    @Getter private ImageView imageView; //Картинка

    public ComputerSystemDiagramDetail(ComputerSystemElementProperty computerSystemElementProperty, Image image, double x, double y) {
        super(x, y);
        imageView = new ImageView(image);
        this.connectors = new ArrayList<>();
        this.elements = new ArrayList<>();
        this.computerSystemElementProperty = computerSystemElementProperty;
        getChildren().add(imageView);
        //Создание Лейбла
        if (computerSystemElementProperty.getAvailabilityFactorProperty() != null) {
            Label label = new Label();
            StringConverter<Number> converter = new NumberStringConverter();
            Bindings.bindBidirectional(label.textProperty(),
                    computerSystemElementProperty.getAvailabilityFactorProperty(),
                    converter
            );
            label.setStyle("-fx-font-weight: bold;");
            getChildren().add(label);
        }
    }

    public static ComputerSystemDiagramDetail createCpuImage(double x, double y, double availabilityFactor){
        return new ComputerSystemDiagramDetail(new ComputerSystemElementProperty(ComputerSystemElementType.CPU, availabilityFactor),
                ComputerSystemPictures.CPU.getImage(),
                x,
                y
        );
    }

    public static ComputerSystemDiagramDetail createRamImage(double x, double y, double availabilityFactor){
        return new ComputerSystemDiagramDetail(new ComputerSystemElementProperty(ComputerSystemElementType.RAM, availabilityFactor),
                ComputerSystemPictures.RAM.getImage(),
                x,
                y
        );
    }

    public static ComputerSystemDiagramDetail createIoImage(double x, double y, double availabilityFactor){
        return new ComputerSystemDiagramDetail(new ComputerSystemElementProperty(ComputerSystemElementType.IO, availabilityFactor),
                ComputerSystemPictures.IO.getImage(),
                x,
                y
        );
    }

    public static ComputerSystemDiagramDetail createPointImage(double x, double y){
        return new ComputerSystemDiagramDetail(new ComputerSystemElementProperty(ComputerSystemElementType.POINT),
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