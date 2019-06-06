package kurlyk.transfer.answer;

import kurlyk.transfer.tasks.TextDto;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = true)
public class TextAnswerDto extends BaseAnswerDto{
    private TextDto entity;
}
