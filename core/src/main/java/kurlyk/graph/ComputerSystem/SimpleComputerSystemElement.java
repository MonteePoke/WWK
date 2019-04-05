package kurlyk.graph.ComputerSystem;

import kurlyk.graph.GraphElement;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.Objects;
import java.util.UUID;

@Getter
@EqualsAndHashCode
public class SimpleComputerSystemElement implements GraphElement<SimpleComputerSystemElement> {
    private UUID uuid;
    private ComputerSystemElementType type;
    private Double availabilityFactor;

    public SimpleComputerSystemElement(ComputerSystemElement computerSystemElement) {
        this.uuid = computerSystemElement.getUuid();
        this.type = computerSystemElement.getType();
        this.availabilityFactor = computerSystemElement.getAvailabilityFactor().get();
    }

    @Override
    public boolean characteristicEquals(SimpleComputerSystemElement element) {
        if (this == element) return true;
        if (!Objects.equals(type, element.type)) return false;
        if (!Objects.equals(availabilityFactor, element.availabilityFactor)) return false;

        return true;
    }
}