package kurlyk.view.computerComplexDiagram;

import com.google.gson.Gson;
import kurlyk.graph.GraphElement;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

public class Graph extends SimpleGraph <GraphElement, DefaultEdge>{

    public Graph() {
        super(DefaultEdge.class);
    }

    public void addElement(GraphElement element){
        addVertex(element);
    }

    public void connectElements(GraphElement elementFrom, GraphElement elementTo){
        addEdge(elementFrom, elementTo);
    }

    public String toJson(){
        return new Gson().toJson(this);
    }

    public Graph fromJson(String json){
        return new Gson().fromJson(json, Graph.class);
    }
}
