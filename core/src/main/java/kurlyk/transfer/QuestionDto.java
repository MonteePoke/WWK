package kurlyk.transfer;

import kurlyk.QuestionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuestionDto {
    private Long id;

    private String name;
    private Integer labNumber;
    private QuestionType questionType;

    private String question;
    private String answer;
}
