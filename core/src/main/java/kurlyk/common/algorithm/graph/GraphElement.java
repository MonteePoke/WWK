package kurlyk.common.algorithm.graph;


public interface GraphElement <T extends GraphElement>{
    boolean characteristicEquals(T element);
}
