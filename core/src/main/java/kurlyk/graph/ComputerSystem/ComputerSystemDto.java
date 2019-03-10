package kurlyk.graph.ComputerSystem;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class ComputerSystemDto {
    private ComputerSystemElementType type;
    private Double availabilityFactor;

    public ComputerSystemDto(ComputerSystemElementType type, Double availabilityFactor) {
        this.type = type;
        this.availabilityFactor = availabilityFactor;
    }

    public ComputerSystemDto(ComputerSystemElementType type) {
        this.type = type;
    }
}
