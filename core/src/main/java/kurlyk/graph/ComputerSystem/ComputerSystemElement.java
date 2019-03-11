package kurlyk.graph.ComputerSystem;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.Objects;
import java.util.UUID;

@Getter
@EqualsAndHashCode
public class ComputerSystemElement {
    private UUID uuid;
    private ComputerSystemElementType type;
    private Double availabilityFactor;

    public ComputerSystemElement(ComputerSystemElementType type, Double availabilityFactor) {
        this.uuid = UUID.randomUUID();
        this.type = type;
        this.availabilityFactor = availabilityFactor;
    }

    public ComputerSystemElement(ComputerSystemElementType type) {
        this(type, null);
    }

    public boolean characteristicEquals(ComputerSystemElement computerSystemElement) {
        if (this == computerSystemElement) return true;
        if (!Objects.equals(type, computerSystemElement.type)) return false;
        if (!Objects.equals(availabilityFactor, computerSystemElement.availabilityFactor)) return false;

        return true;
    }
}
