package kurlyk.graph;


public interface GraphElement <T extends GraphElement>{
    boolean characteristicEquals(T element);
}
