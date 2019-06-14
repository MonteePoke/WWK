package kurlyk.view.task.computerSystemDiagramWindow.computerSystemDiagram;


import javafx.scene.shape.Line;
import javafx.scene.shape.StrokeLineCap;
import lombok.Getter;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.UUID;


public class ComputerSystemDiagramConnector extends Line implements DiagramElement {

    @Getter private ComputerSystemDiagramDetail elementFrom;
    @Getter private ComputerSystemDiagramDetail elementTo;
    @Getter private UUID uuid;

    public ComputerSystemDiagramConnector(ComputerSystemDiagramDetail elementFrom, ComputerSystemDiagramDetail elementTo) {
        super(elementFrom.getLayoutX() + elementFrom.getWidth() / 2,
                elementFrom.getLayoutY() + elementFrom.getHeight() / 2,
                elementTo.getLayoutX() + elementTo.getWidth() / 2,
                elementTo.getLayoutY() + elementTo.getHeight() / 2);
        this.elementFrom = elementFrom;
        this.elementTo = elementTo;
        this.uuid = UUID.randomUUID();
        elementFrom.layoutXProperty().addListener((observable, oldValue, newValue) -> {
            setStartX(newValue.doubleValue() + elementFrom.getWidth() / 2);
            elementFrom.toFront();
        });
        elementFrom.layoutYProperty().addListener((observable, oldValue, newValue) -> {
            setStartY(newValue.doubleValue() + elementFrom.getHeight() / 2);
            elementFrom.toFront();
        });
        elementTo.layoutXProperty().addListener((observable, oldValue, newValue) -> {
            setEndX(newValue.doubleValue() + elementTo.getWidth() / 2);
            elementTo.toFront();
        });
        elementTo.layoutYProperty().addListener((observable, oldValue, newValue) -> {
            setEndY(newValue.doubleValue() + elementTo.getHeight() / 2);
            elementTo.toFront();
        });
        setStrokeWidth(2);
        setStrokeLineCap(StrokeLineCap.ROUND);
        setSmooth(true);
        //Добаввление элементов
    }

    @Override
    public List<? extends DiagramElement> getDiagramElementsForRemove() {
        return Collections.emptyList();
    }

    @Override
    public void prepareForRemoval() {
        elementFrom.getConnectors().remove(this);
        elementTo.getConnectors().remove(this);
    }


    //Условие равенства линий - совпадение элементов на обоих концах линии, независимо он порядка следования
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        ComputerSystemDiagramConnector that = (ComputerSystemDiagramConnector) o;

        return Objects.equals(elementFrom, that.elementTo) && Objects.equals(elementTo, that.elementFrom) ||
                Objects.equals(elementFrom, that.elementFrom) && Objects.equals(elementTo, that.elementTo);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (elementFrom != null ? elementFrom.hashCode() : 0);
        result = 31 * result + (elementTo != null ? elementTo.hashCode() : 0);
        return result;
    }
}
