package kurlyk.graph;

import kurlyk.graph.ComputerSystem.ComputerSystemDto;

public interface FigureGraph {

    void addElement(ComputerSystemDto computerSystemDto);
    void connectElements(ComputerSystemDto elementFrom, ComputerSystemDto elementTo);
}
