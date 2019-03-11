package kurlyk.graph;

import kurlyk.graph.ComputerSystem.ComputerSystemElement;

public interface FigureGraph {

    void add(ComputerSystemElement computerSystemElement);
    void connect(ComputerSystemElement elementFrom, ComputerSystemElement elementTo);
}
