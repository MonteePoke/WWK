package kurlyk;

import kurlyk.graph.GraphSystem;

public class TestMain {

    public static void main(String[] args) {
//        GraphSystem graph = new GraphSystem();
//        ComputerSystemElement element = new ComputerSystemElement(ComputerSystemElementType.CPU, 0d);
//        ComputerSystemElement element2 = new ComputerSystemElement(ComputerSystemElementType.RAM, 0d);
//        ComputerSystemElement element3 = new ComputerSystemElement(ComputerSystemElementType.IO, 0d);
//        graph.addVertex(element);
//        graph.addVertex(element2);
////        graph.addVertex(element3);
//        graph.addEdge(element, element2);
//
//        GraphSystem graphAnother = new GraphSystem();
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

        GraphSystem<Dto> graph = new GraphSystem<>();
        Dto dto1 = new Dto("Another1");
        Dto dto2 = new Dto("Another2");
        graph.add(dto1);
        graph.add(dto2);
        graph.connect(dto1, dto2);

        GraphSystem<Dto> graphAnother = new GraphSystem<>();
        Dto dtoAnother1 = new Dto("Another1");
        Dto dtoAnother2 = new Dto("Another2");
        graphAnother.add(dtoAnother1);
        graphAnother.add(dtoAnother2);
        graphAnother.connect(dtoAnother1, dtoAnother2);

        dtoAnother2.setElement("123");

        System.out.println(graph.isomorfic(graphAnother));
    }
}
