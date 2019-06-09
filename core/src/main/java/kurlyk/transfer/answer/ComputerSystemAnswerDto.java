package kurlyk.transfer.answer;

import kurlyk.transfer.tasks.ComputerSystemDto;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class ComputerSystemAnswerDto extends BaseAnswerDto{
    private ComputerSystemDto entity;

    @Builder
    public ComputerSystemAnswerDto(Long usverId, Long questionId, ComputerSystemDto entity, Integer attemptsNumber) {
        super(usverId, questionId, attemptsNumber);
        this.entity = entity;
    }
}
