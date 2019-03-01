package kurlyk.graph.ComputerSystem;

import kurlyk.graph.FigureGraph;
import lombok.EqualsAndHashCode;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

@EqualsAndHashCode(callSuper = true)
public class ComputerSystemGraph extends SimpleGraph<ComputerSystemGraphElement, DefaultEdge> implements FigureGraph {


    public ComputerSystemGraph() {
        super(DefaultEdge.class);
    }

    public void addElement(ComputerSystemGraphElement computerSystemGraphElement){
        addVertex(computerSystemGraphElement);
    }

    public void connectElements(ComputerSystemGraphElement elementFrom, ComputerSystemGraphElement elementTo){
        addEdge(elementFrom, elementTo);
    }
}
