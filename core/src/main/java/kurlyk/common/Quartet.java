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
    private A a;
    private B b;
    private C c;
    private D d;
}
