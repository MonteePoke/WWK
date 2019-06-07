package kurlyk.transfer.answer;

import kurlyk.transfer.tasks.SelectDto;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class SelectAnswerDto extends BaseAnswerDto{
    private SelectDto entity;

    @Builder
    public SelectAnswerDto(Long userId, Long questionId, SelectDto entity, Integer attemptsNumber) {
        super(userId, questionId, attemptsNumber);
        this.entity = entity;
    }
}
