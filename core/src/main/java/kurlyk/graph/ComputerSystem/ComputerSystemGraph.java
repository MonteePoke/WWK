package kurlyk.graph.ComputerSystem;

import kurlyk.graph.FigureGraph;
import kurlyk.graph.FigureGraphElement;
import lombok.EqualsAndHashCode;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

@EqualsAndHashCode(callSuper = true)
public class ComputerSystemGraph extends SimpleGraph<FigureGraphElement, DefaultEdge> implements FigureGraph {


    public ComputerSystemGraph() {
        super(DefaultEdge.class);
    }

    public void addElement(FigureGraphElement figureGraphElement){
        addVertex(figureGraphElement);
    }

    public void connectElements(FigureGraphElement elementFrom, FigureGraphElement elementTo){
        addEdge(elementFrom, elementTo);
    }
}
