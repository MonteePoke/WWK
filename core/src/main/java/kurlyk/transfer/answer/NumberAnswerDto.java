package kurlyk.transfer.answer;

import kurlyk.transfer.tasks.NumberDto;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class NumberAnswerDto extends BaseAnswerDto{
    private NumberDto entity;

    @Builder
    public NumberAnswerDto(Long usverId, Long questionId, NumberDto entity, Integer attemptsNumber) {
        super(usverId, questionId, attemptsNumber);
        this.entity = entity;
    }
}
