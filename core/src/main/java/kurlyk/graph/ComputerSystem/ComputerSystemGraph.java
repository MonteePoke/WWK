package kurlyk.graph.ComputerSystem;

import kurlyk.graph.FigureGraph;
import lombok.EqualsAndHashCode;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

@EqualsAndHashCode(callSuper = true)
public class ComputerSystemGraph extends SimpleGraph<ComputerSystemElement, DefaultEdge> implements FigureGraph {


    public ComputerSystemGraph() {
        super(DefaultEdge.class);
    }

    public void addElement(ComputerSystemElement computerSystemElement){
        addVertex(computerSystemElement);
    }

    public void connectElements(ComputerSystemElement elementFrom, ComputerSystemElement elementTo){
        addEdge(elementFrom, elementTo);
    }
}
