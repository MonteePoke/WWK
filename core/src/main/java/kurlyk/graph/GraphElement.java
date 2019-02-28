package kurlyk.graph;

import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class GraphElement <T extends Enum> {

    private T type;


}
