package kurlyk.graph;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GraphBaseEntity {

    private GraphEntityType type;
    private Integer value;

    public GraphBaseEntity(GraphEntityType type) {
        this(type, GraphIdFactory.getIdByGraphEntityType(type));
    }
}
