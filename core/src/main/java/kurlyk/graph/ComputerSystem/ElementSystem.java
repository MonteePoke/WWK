package kurlyk.graph.ComputerSystem;

import kurlyk.graph.FigureGraph;
import lombok.Data;
import org.jgrapht.GraphMapping;
import org.jgrapht.alg.isomorphism.VF2GraphIsomorphismInspector;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class ElementSystem<T> implements FigureGraph <T>{

    private Set<ComputerSystemElement> elementSet;
    private Set<ConnectionTuple<ComputerSystemElement>> connectionSet;
    private SimpleGraph<T, DefaultEdge> graph;

    public ElementSystem() {
        elementSet = new HashSet<>();
        connectionSet = new HashSet<>();
        graph = new SimpleGraph<>(DefaultEdge.class);
    }

    public void add(ComputerSystemElement element){
        elementSet.add(element);
    }

    public void remove(ComputerSystemElement element){
        elementSet.remove(element);
    }

    public void connect(ComputerSystemElement elementFrom, ComputerSystemElement elementTo){
        connectionSet.add(new ConnectionTuple<>()); //elementFrom, elementTo
    }

    public void disconnect(ComputerSystemElement elementFrom, ComputerSystemElement elementTo){
        connectionSet.remove((elementFrom, elementTo);
    }

    private SimpleGraph<ComputerSystemElement, DefaultEdge> buildGraph(){

    }

    public boolean isomorfic(ElementSystem elementSystem){
        VF2GraphIsomorphismInspector<ComputerSystemElement, DefaultEdge> inspector = new VF2GraphIsomorphismInspector<>(this, elementSystem);
        Iterator<GraphMapping<ComputerSystemElement, DefaultEdge>> iterator = inspector.getMappings();
        System.out.println("isomorphismExists: " + inspector.isomorphismExists());
        if (!inspector.isomorphismExists()){
            return false;
        }
        while (iterator.hasNext()){
            GraphMapping<ComputerSystemElement, DefaultEdge> mapping = iterator.next();
            boolean matchingResult = vertexSet()
                    .stream()
                    .allMatch(computerSystemElement ->
                            computerSystemElement.characteristicEquals(mapping.getVertexCorrespondence(computerSystemElement, true))
                    );
            if (matchingResult){
                return true;
            }
        }
        return false;
    }

    @Data
    private class ConnectionTuple <T>{
        private T connectionFrom;
        private T connectionTo;
    }
}
