package kurlyk.view.computerSystemDiagram;


import javafx.scene.shape.Line;
import javafx.scene.shape.StrokeLineCap;
import kurlyk.view.common.draw.DiagramElement;

import java.util.Objects;
import java.util.UUID;


public class ComputerSystemDiagramConnector extends Line implements DiagramElement {

    private ComputerSystemDiagramElement elementFrom;
    private ComputerSystemDiagramElement elementTo;
    private UUID uuid;

    public ComputerSystemDiagramConnector(ComputerSystemDiagramElement elementFrom, ComputerSystemDiagramElement elementTo) {
        super(elementFrom.getX() + elementFrom.getImage().getWidth() / 2,
                elementFrom.getY() + elementFrom.getImage().getHeight() / 2,
                elementTo.getX() + elementTo.getImage().getWidth() / 2,
                elementTo.getY() + elementTo.getImage().getHeight() / 2);
        this.elementFrom = elementFrom;
        this.elementTo = elementTo;
        this.uuid = UUID.randomUUID();
        elementFrom.xProperty().addListener((observable, oldValue, newValue) -> {
            setStartX(newValue.doubleValue() + elementFrom.getImage().getWidth() / 2);
            elementFrom.toFront();
        });
        elementFrom.yProperty().addListener((observable, oldValue, newValue) -> {
            setStartY(newValue.doubleValue() + elementFrom.getImage().getHeight() / 2);
            elementFrom.toFront();
        });
        elementTo.xProperty().addListener((observable, oldValue, newValue) -> {
            setEndX(newValue.doubleValue() + elementTo.getImage().getWidth() / 2);
            elementTo.toFront();
        });
        elementTo.yProperty().addListener((observable, oldValue, newValue) -> {
            setEndY(newValue.doubleValue() + elementTo.getImage().getHeight() / 2);
            elementTo.toFront();
        });
        setStrokeWidth(5);
        setStrokeLineCap(StrokeLineCap.ROUND);
        setSmooth(true);
    }

    public ComputerSystemDiagramElement getElementFrom() {
        return elementFrom;
    }

    public ComputerSystemDiagramElement getElementTo() {
        return elementTo;
    }

    @Override
    public UUID getUuid() {
        return uuid;
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
