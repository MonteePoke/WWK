package kurlyk;

import org.jgrapht.GraphMapping;
import org.jgrapht.alg.isomorphism.VF2GraphIsomorphismInspector;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import java.util.Iterator;

public class TestMain {

    public static void main(String[] args) {
//        ElementSystem graph = new ElementSystem();
//        ComputerSystemElement element = new ComputerSystemElement(ComputerSystemElementType.CPU, 0d);
//        ComputerSystemElement element2 = new ComputerSystemElement(ComputerSystemElementType.RAM, 0d);
//        ComputerSystemElement element3 = new ComputerSystemElement(ComputerSystemElementType.IO, 0d);
//        graph.addVertex(element);
//        graph.addVertex(element2);
////        graph.addVertex(element3);
//        graph.addEdge(element, element2);
//
//        ElementSystem graphAnother = new ElementSystem();
//        ComputerSystemElement elementAnother = new ComputerSystemElement(ComputerSystemElementType.CPU, 0d);
//        ComputerSystemElement elementAnother2 = new ComputerSystemElement(ComputerSystemElementType.RAM, 0d);
//        ComputerSystemElement elementAnother3 = new ComputerSystemElement(ComputerSystemElementType.IO, 0d);
//        graphAnother.addVertex(elementAnother);
//        graphAnother.addVertex(elementAnother2);
////        graphAnother.addVertex(elementAnother3);
//        graphAnother.addEdge(elementAnother2, elementAnother);
//
//        graphAnother.addVertex(elementAnother3);
//        graphAnother.addEdge(elementAnother2, elementAnother3);
//        graphAnother.remove(elementAnother3);
//
//        System.out.println(graph.isomorfic(graphAnother));

        SimpleGraph<Dto, DefaultEdge> graph = new SimpleGraph<>(DefaultEdge.class);
        Dto dto1 = new Dto("1");
        Dto dto2 = new Dto("2");
        graph.addVertex(dto1);
        graph.addVertex(dto2);
        graph.addEdge(dto1, dto2);

        SimpleGraph<Dto, DefaultEdge> graphAnother = new SimpleGraph<>(DefaultEdge.class);
        Dto dtoAnother1 = new Dto("Another1");
        Dto dtoAnother2 = new Dto("Another2");
        graphAnother.addVertex(dtoAnother1);
        graphAnother.addVertex(dtoAnother2);
        graphAnother.addEdge(dtoAnother1, dtoAnother2);

        dtoAnother2.setStr("123");

        System.out.println(isomorfic(graph, graphAnother));
    }

    private static boolean isomorfic(SimpleGraph<Dto, DefaultEdge> graph1, SimpleGraph<Dto, DefaultEdge> graph2){
        VF2GraphIsomorphismInspector<Dto, DefaultEdge> inspector = new VF2GraphIsomorphismInspector<>(graph1, graph2);
        Iterator<GraphMapping<Dto, DefaultEdge>> iterator = inspector.getMappings();
        System.out.println("isomorphismExists: " + inspector.isomorphismExists());
        if (!inspector.isomorphismExists()){
            return false;
        }
        while (iterator.hasNext()){
            GraphMapping<Dto, DefaultEdge> mapping = iterator.next();
            boolean matchingResult = graph1.vertexSet()
                    .stream()
                    .allMatch(dto ->
                            dto.equals(mapping.getVertexCorrespondence(dto, true))
                    );
            if (matchingResult){
                return true;
            }
        }
        return false;
    }
}
