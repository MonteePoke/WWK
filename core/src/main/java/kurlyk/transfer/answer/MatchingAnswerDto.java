package kurlyk.transfer.answer;

import kurlyk.transfer.tasks.MatchingDto;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class MatchingAnswerDto extends BaseAnswerDto{
    private MatchingDto entity;

    @Builder
    public MatchingAnswerDto(Long usverId, Long questionId, MatchingDto entity, Integer attemptsNumber) {
        super(usverId, questionId, attemptsNumber);
        this.entity = entity;
    }
}
