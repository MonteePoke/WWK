package kurlyk.common.algorithm.graph.ComputerSystem;

import kurlyk.common.algorithm.graph.GraphElement;
import lombok.*;

import java.util.Objects;
import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
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