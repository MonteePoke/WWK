package kurlyk.transfer.tasks;

import kurlyk.common.algorithm.graph.GraphSystem;
import kurlyk.common.algorithm.graph.SimpleGraphSystem;
import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class ComputerSystemDto{
    private SimpleGraphSystem simpleGraphSystem;

    public ComputerSystemDto(GraphSystem graphSystem) {
        this.simpleGraphSystem = new SimpleGraphSystem(graphSystem);
    }

    public GraphSystem getGraphSystem(){
        return simpleGraphSystem.toGraphSystem();
    }

    public void setSimpleGraphSystem(SimpleGraphSystem simpleGraphSystem) {
        this.simpleGraphSystem = simpleGraphSystem;
    }
}
