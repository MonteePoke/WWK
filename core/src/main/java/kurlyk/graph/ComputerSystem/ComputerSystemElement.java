package kurlyk.graph.ComputerSystem;

import kurlyk.common.classesMadeByStas.SelfMadeSimpleDoubleProperty;
import kurlyk.graph.GraphElement;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.Objects;
import java.util.UUID;

@Getter
@EqualsAndHashCode
public class ComputerSystemElement implements GraphElement<ComputerSystemElement> {
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

    @Override
    public boolean characteristicEquals(ComputerSystemElement element) {
        if (this == element) return true;
        if (!Objects.equals(type, element.type)) return false;
        if (!Objects.equals(availabilityFactor, element.availabilityFactor)) return false;

        return true;
    }
}