package kurlyk.transfer.answer;

import kurlyk.transfer.tasks.NumberDto;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = true)
public class NumberAnswerDto extends BaseAnswerDto{
    private NumberDto entity;
}
