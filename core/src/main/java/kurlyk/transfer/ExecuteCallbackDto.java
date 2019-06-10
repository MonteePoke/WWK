package kurlyk.transfer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExecuteCallbackDto {
    private Boolean isExecuted;
    private ResultDto resultDto;
}
