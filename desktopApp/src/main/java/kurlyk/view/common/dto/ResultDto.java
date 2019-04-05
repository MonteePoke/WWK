package kurlyk.view.common.dto;

import kurlyk.models.UserProgress;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResultDto {
    private Integer correctAnswerNumber;
    private Integer wrongAnswerNumber;
    private Double score;
    private Integer errorsNumber;

    public ResultDto(List<UserProgress> userProgresses) {
        this.correctAnswerNumber = userProgresses
                .stream()
                .mapToInt(userProgress -> userProgress.getScore() > 0 ? 1 : 0)
                .sum();
        this.wrongAnswerNumber = userProgresses
                .stream()
                .mapToInt(userProgress -> userProgress.getScore() <= 0 ? 1 : 0)
                .sum();
        this.score = userProgresses
                .stream()
                .mapToDouble(UserProgress::getScore)
                .sum();
        this.errorsNumber = userProgresses
                .stream()
                .mapToInt(UserProgress::getErrorsNumber)
                .sum();
    }
}
