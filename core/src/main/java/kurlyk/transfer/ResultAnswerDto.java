package kurlyk.transfer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResultAnswerDto {
    private long score;
    private long questionId;
    @Builder.Default private boolean questionNotFound = false;
    @Builder.Default private boolean serverError = false;
}
