package kurlyk.graph;

import java.util.HashMap;
import java.util.Map;

public class GraphIdFactory {
    private static GraphIdFactory ourInstance = new GraphIdFactory();
    private static Map<GraphEntityType, Integer> dataBaseIds = new HashMap<>();

    public static GraphIdFactory getInstance() {
        return ourInstance;
    }

    private GraphIdFactory() {
    }

    public static Integer getIdByGraphEntityType(GraphEntityType type){
        if(!dataBaseIds.containsKey(type)){
            dataBaseIds.put(type, 0);
        }
        dataBaseIds.put(type, dataBaseIds.get(type) + 1);
        return dataBaseIds.get(type);
    }
}
