package kurlyk.graph.ComputerSystem;

import kurlyk.graph.FigureGraph;
import lombok.EqualsAndHashCode;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

@EqualsAndHashCode(callSuper = true)
public class ComputerSystemGraph extends SimpleGraph<ComputerSystemDto, DefaultEdge> implements FigureGraph {


    public ComputerSystemGraph() {
        super(DefaultEdge.class);
    }

    public void addElement(ComputerSystemDto computerSystemDto){
        addVertex(computerSystemDto);
    }

    public void connectElements(ComputerSystemDto elementFrom, ComputerSystemDto elementTo){
        addEdge(elementFrom, elementTo);
    }
}
