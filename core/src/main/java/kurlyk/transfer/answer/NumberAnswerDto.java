package kurlyk.transfer.answer;

import kurlyk.transfer.tasks.NumberDto;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class NumberAnswerDto extends BaseAnswerDto{
    private NumberDto entity;

    @Builder
    public NumberAnswerDto(Long userId, Long questionId, NumberDto entity, Integer attemptsNumber) {
        super(userId, questionId, attemptsNumber);
        this.entity = entity;
    }
}
