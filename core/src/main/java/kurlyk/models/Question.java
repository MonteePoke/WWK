package kurlyk.models;

import kurlyk.QuestionType;
import kurlyk.WhenShowAnswer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(value = EnumType.STRING)
    private QuestionType questionType;
    private Integer number;
    private String name;
    private Integer attemptsNumber;
    private Boolean interrupt;
    private Long score;

    @Enumerated(value = EnumType.STRING)
    private WhenShowAnswer whenShowAnswer;
    private Boolean negativeScore;
    private Long decScore;
    private Double scoreMultiplier;
    private Integer variantsNumber;
    private Boolean skipQuestion;
    private LocalDateTime timer;

    @Lob
    @Column(columnDefinition = "CLOB")
    private String question;
    @Lob
    @Column(columnDefinition = "CLOB")
    private String answer;

    // TODO БЛЯТЬ!!!
    public Question(
            Long id,
            QuestionType questionType,
            Long score,
            String name,
            Integer attemptsNumber,
            Integer number
    ) {
        this.id = id;
        this.questionType = questionType;
        this.score = score;
        this.name = name;
        this.attemptsNumber = attemptsNumber;
        this.number = number;
    }
}
