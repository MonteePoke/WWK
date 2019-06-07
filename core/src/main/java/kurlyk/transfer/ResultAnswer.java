package kurlyk.transfer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResultAnswer {
    private long score;
    private boolean questionNotFound = false;
    private boolean serverError = false;
}
