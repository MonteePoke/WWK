package kurlyk.graph.ComputerSystem;

import kurlyk.graph.FigureGraph;
import lombok.EqualsAndHashCode;
import org.jgrapht.GraphMapping;
import org.jgrapht.alg.isomorphism.VF2GraphIsomorphismInspector;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import java.util.Iterator;

@EqualsAndHashCode(callSuper = true)
public class ComputerSystem extends SimpleGraph<ComputerSystemElement, DefaultEdge> implements FigureGraph {


    public ComputerSystem() {
        super(DefaultEdge.class);
    }

    public void add(ComputerSystemElement computerSystemElement){
        addVertex(computerSystemElement);
    }

    public void remove(ComputerSystemElement computerSystemElement){
        removeVertex(computerSystemElement);
    }

    public void connect(ComputerSystemElement elementFrom, ComputerSystemElement elementTo){
        addEdge(elementFrom, elementTo);
    }

    public void disconnect(ComputerSystemElement elementFrom, ComputerSystemElement elementTo){
        removeEdge(elementFrom, elementTo);
    }

    public boolean isomorfic(ComputerSystem computerSystem){
        VF2GraphIsomorphismInspector<ComputerSystemElement, DefaultEdge> inspector = new VF2GraphIsomorphismInspector<>(this, computerSystem);
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
}
