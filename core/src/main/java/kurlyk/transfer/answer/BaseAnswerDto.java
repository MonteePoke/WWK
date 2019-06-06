package kurlyk.transfer.answer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
abstract public class BaseAnswerDto {
    private Long userId;
    private Long labWorkId;
    private Long taskId;
    private Long questionId;
}
