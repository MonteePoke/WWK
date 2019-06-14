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
public class Trio <A, B, C> implements Serializable {
    private A valueA;
    private B valueB;
    private C valueC;
}