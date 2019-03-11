package kurlyk.view.drawComputerSystemWindow.computerSystemDiagram;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import kurlyk.graph.ComputerSystem.ComputerSystemElementType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ComputerSystemDiagramElementProperty {
    private ComputerSystemElementType type;
    private DoubleProperty availabilityFactorProperty;

    public ComputerSystemDiagramElementProperty(ComputerSystemElementType type, Double availabilityFactorProperty) {
        this.type = type;
        this.availabilityFactorProperty = new SimpleDoubleProperty(availabilityFactorProperty);
    }

    public ComputerSystemDiagramElementProperty(ComputerSystemElementType type) {
        this.type = type;
    }
}
