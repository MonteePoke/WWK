package kurlyk.model;

import kurlyk.QuestionType;
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
    private Long score;

    private Boolean negativeScore;
    private Long decScore;
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
            Long score,
            Boolean negativeScore,
            Long decScore,
            Boolean skipQuestion,
            Duration interval
            ) {
        this.id = id;
        this.task = task;
        this.questionType = questionType;
        this.number = number;
        this.name = name;
        this.attemptsNumber = attemptsNumber;
        this.score = score;
        this.negativeScore = negativeScore;
        this.decScore = decScore;
        this.skipQuestion = skipQuestion;
        this.interval = interval;
    }

    public Question(
            Long id,
            Integer number,
            Long taskId,
            Integer taskNumber
    ) {
        this.id = id;
        this.number = number;
        this.task = Task.builder().id(taskId).number(taskNumber).build();
    }
}
