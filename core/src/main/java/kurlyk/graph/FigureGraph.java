package kurlyk.graph;

import kurlyk.graph.ComputerSystem.ComputerSystemGraphElement;

public interface FigureGraph {

    void addElement(ComputerSystemGraphElement computerSystemGraphElement);
    void connectElements(ComputerSystemGraphElement elementFrom, ComputerSystemGraphElement elementTo);
}
