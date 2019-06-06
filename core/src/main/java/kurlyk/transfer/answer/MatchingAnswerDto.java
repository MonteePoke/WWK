package kurlyk.transfer.answer;

import kurlyk.transfer.tasks.MatchingDto;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = true)
public class MatchingAnswerDto extends BaseAnswerDto{
    private MatchingDto entity;
}
