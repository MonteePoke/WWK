package kurlyk.transfer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResultDto {
    private Type type;
    private Long score;
    private Long maxScore;
    private Integer questionsNumber;
    private Integer attemptsNumber;

    public boolean isExexute(){
        return score * 2 >= maxScore;
    }

    public enum Type{
        TEST,
        LAB_WORK;
    }
}
