package kurlyk.graph;


public interface FigureGraph <T> {

    void add(T element);

    void remove(T element);

    void connect(T elementFrom, T elementTo);

    void disconnect(T elementFrom, T elementTo);
}
