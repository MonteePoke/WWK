package kurlyk.transfer.tasks;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class NumberDto{
    private Double number;
    //Количество значимых знаков после запятой
    private Integer error;
}
