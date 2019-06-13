package kurlyk.transfer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuestionForTableDto {
    private Long questionId;
    private Integer questionNumber;
    private String questionName;

    private Long taskId;
    private Integer taskNumber;
    private String taskName;

    private Long labWorkId;
    private Integer labWorkNumber;
    private String labWorkName;
}
