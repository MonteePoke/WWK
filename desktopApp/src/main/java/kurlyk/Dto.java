package kurlyk;

import kurlyk.graph.GraphElement;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
public class Dto implements GraphElement<Dto> {
    private String element;

    @Override
    public boolean characteristicEquals(Dto element) {
        return this.equals(element);
    }
}
