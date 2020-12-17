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
public class Quintet<A, B, C, D, E> implements Serializable {
    private A a;
    private B b;
    private C c;
    private D d;
    private E e;
}
