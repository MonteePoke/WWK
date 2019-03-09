package kurlyk.graph.ComputerSystem;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class ComputerSystemElement {
    private ComputerSystemElementTypes type;
    private Double availabilityFactor;

    public ComputerSystemElement(ComputerSystemElementTypes type, Double availabilityFactor) {
        this.type = type;
        this.availabilityFactor = availabilityFactor;
    }

    public ComputerSystemElement(ComputerSystemElementTypes type) {
        this.type = type;
    }
}
