package kurlyk.transfer.tasks;

import kurlyk.graph.GraphSystem;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
@AllArgsConstructor
public class ComputerSystemDto {
    private GraphSystem graphSystem;

    public ComputerSystemDto() {
        this.graphSystem = new GraphSystem();
    }
}
