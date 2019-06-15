package kurlyk.transfer.answer;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
abstract public class BaseAnswerDto {
    private Long usverId;
    private Long questionId;
    private Integer attemptsNumber;
}
