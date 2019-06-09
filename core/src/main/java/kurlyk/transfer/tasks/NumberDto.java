package kurlyk.transfer.tasks;

import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class NumberDto{
    private Double number;
    //Количество значимых знаков после запятой
    private Integer accuracy;
}
