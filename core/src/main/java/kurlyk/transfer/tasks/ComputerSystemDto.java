package kurlyk.transfer.tasks;

import kurlyk.graph.ComputerSystem.ComputerSystemElement;
import kurlyk.graph.GraphSystem;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
@AllArgsConstructor
public class ComputerSystemDto {
    private GraphSystem<ComputerSystemElement> graphSystem;

    public ComputerSystemDto() {
        this.graphSystem = new GraphSystem<>();
    }
}
