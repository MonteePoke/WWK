package kurlyk.transfer.answer;

import kurlyk.transfer.tasks.SelectDto;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class SelectAnswerDto extends BaseAnswerDto{
    private SelectDto entity;

    @Builder
    public SelectAnswerDto(Long usverId, Long questionId, SelectDto entity, Integer attemptsNumber) {
        super(usverId, questionId, attemptsNumber);
        this.entity = entity;
    }
}
