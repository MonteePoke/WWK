package kurlyk.transfer.answer;

import kurlyk.transfer.tasks.ComputerSystemDto;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = true)
public class ComputerSystemAnswerDto extends BaseAnswerDto{
    private ComputerSystemDto entity;
}
