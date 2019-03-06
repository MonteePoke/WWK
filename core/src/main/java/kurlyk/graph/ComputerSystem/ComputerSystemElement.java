package kurlyk.graph.ComputerSystem;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
@AllArgsConstructor
public class ComputerSystemElement {
    private ComputerSystemElementTypes type;
    private double availabilityFactor;

}
