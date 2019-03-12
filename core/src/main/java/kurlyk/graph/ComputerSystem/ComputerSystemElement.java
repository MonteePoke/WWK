package kurlyk.graph.ComputerSystem;

import kurlyk.common.classesMadeByStas.SelfMadeSimpleDoubleProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.Objects;
import java.util.UUID;

@Getter
@EqualsAndHashCode
public class ComputerSystemElement {
    private UUID uuid;
    private ComputerSystemElementType type;
    private SelfMadeSimpleDoubleProperty availabilityFactor;

    public ComputerSystemElement(ComputerSystemElementType type, SelfMadeSimpleDoubleProperty availabilityFactor) {
        this.uuid = UUID.randomUUID();
        this.type = type;
        this.availabilityFactor = availabilityFactor;
    }

    public ComputerSystemElement(ComputerSystemElementType type, double availabilityFactor) {
        this(type, new SelfMadeSimpleDoubleProperty(availabilityFactor));
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