package kurlyk.common.algorithm.graph.ComputerSystem;

import kurlyk.common.SelfMadeSimpleDoubleProperty;
import kurlyk.common.algorithm.graph.GraphElement;
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

    public ComputerSystemElement(ComputerSystemElementType type, double availabilityFactor) {
        this(UUID.randomUUID(), type, new SelfMadeSimpleDoubleProperty(availabilityFactor));
    }

    public ComputerSystemElement(UUID uuid, ComputerSystemElementType type, double availabilityFactor) {
        this(uuid, type, new SelfMadeSimpleDoubleProperty(availabilityFactor));
    }

    public ComputerSystemElement(UUID uuid, ComputerSystemElementType type, SelfMadeSimpleDoubleProperty availabilityFactor) {
        this.uuid = uuid;
        this.type = type;
        this.availabilityFactor = availabilityFactor;
    }

    @Override
    public boolean characteristicEquals(ComputerSystemElement element) {
        if (this == element) return true;
        if (!Objects.equals(type, element.type)) return false;
        if (!Objects.equals(availabilityFactor, element.availabilityFactor)) return false;

        return true;
    }
}