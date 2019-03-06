package kurlyk.graph;

import kurlyk.graph.ComputerSystem.ComputerSystemElement;

public interface FigureGraph {

    void addElement(ComputerSystemElement computerSystemElement);
    void connectElements(ComputerSystemElement elementFrom, ComputerSystemElement elementTo);
}
