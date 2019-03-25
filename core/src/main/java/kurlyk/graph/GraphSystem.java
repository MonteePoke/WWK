package kurlyk.graph;

import javafx.util.Pair;
import kurlyk.graph.ComputerSystem.ComputerSystemElement;
import org.jgrapht.GraphMapping;
import org.jgrapht.alg.isomorphism.VF2GraphIsomorphismInspector;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.stream.Collectors;

public class GraphSystem{

    private Set<ComputerSystemElement> elementSet;
    private Set<Pair<ComputerSystemElement, ComputerSystemElement>> connectionSet;


    public GraphSystem() {
        elementSet = new HashSet<>();
        connectionSet = new HashSet<>();
    }

    public void add(ComputerSystemElement element){
        elementSet.add(element);
    }

    public void remove(ComputerSystemElement element){
        elementSet.remove(element);
    }

    public void connect(ComputerSystemElement elementFrom, ComputerSystemElement elementTo){
        connectionSet.add(new Pair<>(elementFrom, elementTo));
    }

    public void disconnect(ComputerSystemElement elementFrom, ComputerSystemElement elementTo){
        connectionSet.remove(new Pair<>(elementFrom, elementTo));
    }

    private SimpleGraph<ComputerSystemElement, DefaultEdge> buildGraph(){
        SimpleGraph<ComputerSystemElement, DefaultEdge> graph = new SimpleGraph<>(DefaultEdge.class);

        //Блять
        Set<Pair<ComputerSystemElement, ComputerSystemElement>> newConnectionSet = new HashSet<>();
        connectionSet.forEach(connection -> {
            ComputerSystemElement keyElement = elementSet.stream()
                    .filter(element -> element.getUuid().equals(connection.getKey().getUuid()))
                    .collect(Collectors.toList())
                    .get(0);
            ComputerSystemElement valueElement = elementSet.stream()
                    .filter(element -> element.getUuid().equals(connection.getValue().getUuid()))
                    .collect(Collectors.toList())
                    .get(0);
            newConnectionSet.add(new Pair<>(keyElement, valueElement));
        });

        elementSet.forEach(graph::addVertex);
        newConnectionSet.forEach(connection -> graph.addEdge(connection.getKey(), connection.getValue()));
        return graph;
    }

    @SuppressWarnings("unchecked")
    public boolean isomorfic(GraphSystem graphSystem){
        SimpleGraph<ComputerSystemElement, DefaultEdge> thisGraph = this.buildGraph();
        SimpleGraph<ComputerSystemElement, DefaultEdge> thatGraph = graphSystem.buildGraph();
        VF2GraphIsomorphismInspector<ComputerSystemElement, DefaultEdge> inspector = new VF2GraphIsomorphismInspector<>(thisGraph, thatGraph);
        Iterator<GraphMapping<ComputerSystemElement, DefaultEdge>> iterator = inspector.getMappings();
        System.out.println("isomorphismExists: " + inspector.isomorphismExists());
        if (!inspector.isomorphismExists()){
            return false;
        }
        while (iterator.hasNext()){
            GraphMapping<ComputerSystemElement, DefaultEdge> mapping = iterator.next();
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
