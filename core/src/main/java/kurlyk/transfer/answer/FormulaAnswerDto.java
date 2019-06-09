package kurlyk.transfer.answer;

import kurlyk.transfer.tasks.FormulaDto;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class FormulaAnswerDto extends BaseAnswerDto{
    private FormulaDto entity;

    @Builder
    public FormulaAnswerDto(Long usverId, Long questionId, FormulaDto entity, Integer attemptsNumber) {
        super(usverId, questionId, attemptsNumber);
        this.entity = entity;
    }
}
