package kurlyk.transfer.answer;

import kurlyk.transfer.tasks.TextDto;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class TextAnswerDto extends BaseAnswerDto{
    private TextDto entity;

    @Builder
    public TextAnswerDto(Long usverId, Long questionId, TextDto entity, Integer attemptsNumber) {
        super(usverId, questionId, attemptsNumber);
        this.entity = entity;
    }
}
