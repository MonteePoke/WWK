package kurlyk.transfer.answer;

import kurlyk.transfer.tasks.TextDto;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class TextAnswerDto extends BaseAnswerDto{
    private TextDto entity;

    @Builder
    public TextAnswerDto(Long usverId, Long questionId, TextDto entity, Integer attemptsNumber) {
        super(usverId, questionId, attemptsNumber);
        this.entity = entity;
    }
}
