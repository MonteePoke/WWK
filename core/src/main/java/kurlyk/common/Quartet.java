package kurlyk.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Quartet<A, B, C, D> implements Serializable {
    private A valueA;
    private B valueB;
    private C valueC;
    private D valueD;
}
