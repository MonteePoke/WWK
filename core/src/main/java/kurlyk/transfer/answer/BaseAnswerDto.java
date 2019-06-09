package kurlyk.transfer.answer;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
abstract public class BaseAnswerDto {
    private Long usverId;
    private Long questionId;
    private Integer attemptsNumber;
}
