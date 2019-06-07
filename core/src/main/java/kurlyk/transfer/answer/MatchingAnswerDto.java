package kurlyk.transfer.answer;

import kurlyk.transfer.tasks.MatchingDto;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class MatchingAnswerDto extends BaseAnswerDto{
    private MatchingDto entity;

    @Builder
    public MatchingAnswerDto(Long userId, Long questionId, MatchingDto entity, Integer attemptsNumber) {
        super(userId, questionId, attemptsNumber);
        this.entity = entity;
    }
}
