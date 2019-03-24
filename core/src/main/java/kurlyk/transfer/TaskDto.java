package kurlyk.transfer;

import kurlyk.LabType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaskDto {
    private Long id;

    private String name;
    private Integer labNumber;
    private LabType labType;

    private String question;
    private String answer;
}
