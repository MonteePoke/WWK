package kurlyk.graph;

import javafx.util.Pair;
import org.jgrapht.GraphMapping;
import org.jgrapht.alg.isomorphism.VF2GraphIsomorphismInspector;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class GraphSystem<T extends GraphElement>{

    private Set<T> elementSet;
    private Set<Pair<T, T>> connectionSet;


    public GraphSystem() {
        elementSet = new HashSet<>();
        connectionSet = new HashSet<>();
    }

    public void add(T element){
        elementSet.add(element);
    }

    public void remove(T element){
        elementSet.remove(element);
    }

    public void connect(T elementFrom, T elementTo){
        connectionSet.add(new Pair<>(elementFrom, elementTo));
    }

    public void disconnect(T elementFrom, T elementTo){
        connectionSet.remove(new Pair<>(elementFrom, elementTo));
    }

    private SimpleGraph<T, DefaultEdge> buildGraph(){
        SimpleGraph<T, DefaultEdge> graph = new SimpleGraph<>(DefaultEdge.class);
        elementSet.forEach(graph::addVertex);
        connectionSet.forEach(connection -> graph.addEdge(connection.getKey(), connection.getValue()));
        return graph;
    }

    @SuppressWarnings("unchecked")
    public boolean isomorfic(GraphSystem<T> graphSystem){
        SimpleGraph<T, DefaultEdge> thisGraph = this.buildGraph();
        SimpleGraph<T, DefaultEdge> thatGraph = graphSystem.buildGraph();
        VF2GraphIsomorphismInspector<T, DefaultEdge> inspector = new VF2GraphIsomorphismInspector<>(thisGraph, thatGraph);
        Iterator<GraphMapping<T, DefaultEdge>> iterator = inspector.getMappings();
        System.out.println("isomorphismExists: " + inspector.isomorphismExists());
        if (!inspector.isomorphismExists()){
            return false;
        }
        while (iterator.hasNext()){
            GraphMapping<T, DefaultEdge> mapping = iterator.next();
            boolean matchingResult = thisGraph.vertexSet()
                    .stream()
                    .allMatch(graphElement ->
                            graphElement.characteristicEquals(mapping.getVertexCorrespondence(graphElement, true))
                    );
            if (matchingResult){
                return true;
            }
        }
        return false;
    }
}
