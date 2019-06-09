package kurlyk.transfer.tasks;

import kurlyk.graph.GraphSystem;
import kurlyk.graph.SimpleGraphSystem;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class ComputerSystemDto{
    private SimpleGraphSystem simpleGraphSystem;

    public ComputerSystemDto() {
        this.simpleGraphSystem = new SimpleGraphSystem();
    }

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
