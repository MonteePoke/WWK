package kurlyk.models;

import kurlyk.QuestionType;
import kurlyk.WhenShowAnswer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Duration;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Task task;

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
    private Duration interval;

    @Lob
    @Column(columnDefinition = "CLOB")
    private String question;
    @Lob
    @Column(columnDefinition = "CLOB")
    private String answer;


    public Question(
            Long id,
            Task task,
            QuestionType questionType,
            Integer number,
            String name,
            Integer attemptsNumber,
            Boolean interrupt,
            Long score,
            WhenShowAnswer whenShowAnswer,
            Boolean negativeScore,
            Long decScore,
            Double scoreMultiplier,
            Integer variantsNumber,
            Boolean skipQuestion,
            Duration interval
            ) {
        this.id = id;
        this.task = task;
        this.questionType = questionType;
        this.number = number;
        this.name = name;
        this.attemptsNumber = attemptsNumber;
        this.interrupt = interrupt;
        this.score = score;
        this.whenShowAnswer = whenShowAnswer;
        this.negativeScore = negativeScore;
        this.decScore = decScore;
        this.scoreMultiplier = scoreMultiplier;
        this.variantsNumber = variantsNumber;
        this.skipQuestion = skipQuestion;
        this.interval = interval;
    }
}
