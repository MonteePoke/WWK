package kurlyk;

import kurlyk.graph.ComputerSystem.ComputerSystem;
import kurlyk.graph.ComputerSystem.ComputerSystemElement;
import kurlyk.graph.ComputerSystem.ComputerSystemElementType;

public class TestMain {

    public static void main(String[] args) {
        ComputerSystem graph = new ComputerSystem();
        ComputerSystemElement element = new ComputerSystemElement(ComputerSystemElementType.CPU, 0d);
        ComputerSystemElement element2 = new ComputerSystemElement(ComputerSystemElementType.RAM, 0d);
        ComputerSystemElement element3 = new ComputerSystemElement(ComputerSystemElementType.IO, 0d);
        graph.addVertex(element);
        graph.addVertex(element2);
        graph.addVertex(element3);
        graph.addEdge(element, element2);

        ComputerSystem graphAnother = new ComputerSystem();
        ComputerSystemElement elementAnother = new ComputerSystemElement(ComputerSystemElementType.CPU, 0d);
        ComputerSystemElement elementAnother2 = new ComputerSystemElement(ComputerSystemElementType.RAM, 0d);
        ComputerSystemElement elementAnother3 = new ComputerSystemElement(ComputerSystemElementType.CPU, 0d);
        graphAnother.addVertex(elementAnother);
        graphAnother.addVertex(elementAnother2);
        graphAnother.addVertex(elementAnother3);
        graphAnother.addEdge(elementAnother2, elementAnother);

        System.out.println(graph.isomorfic(graphAnother));
    }
}
