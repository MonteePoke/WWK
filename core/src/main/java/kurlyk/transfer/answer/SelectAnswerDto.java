package kurlyk.transfer.answer;

import kurlyk.transfer.tasks.SelectDto;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = true)
public class SelectAnswerDto extends BaseAnswerDto{
    private SelectDto entity;
}
