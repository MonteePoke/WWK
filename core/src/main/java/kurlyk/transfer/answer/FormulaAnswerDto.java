package kurlyk.transfer.answer;

import kurlyk.transfer.tasks.FormulaDto;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = true)
public class FormulaAnswerDto extends BaseAnswerDto{
    private FormulaDto entity;
}
