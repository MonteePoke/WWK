package kurlyk.graph;

public interface FigureGraph {

    void addElement(FigureGraphElement figureGraphElement);
    void connectElements(FigureGraphElement elementFrom, FigureGraphElement elementTo);
}
