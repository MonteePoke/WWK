package kurlyk.transfer.answer;

import kurlyk.transfer.tasks.SortingDto;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class SortingAnswerDto extends BaseAnswerDto{
    private SortingDto entity;

    @Builder
    public SortingAnswerDto(Long usverId, Long questionId, SortingDto entity, Integer attemptsNumber) {
        super(usverId, questionId, attemptsNumber);
        this.entity = entity;
    }
}
