package kurlyk.models;

import kurlyk.QuestionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


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
    private String name;
    private Integer number;
    private Double score;
    private Integer atemptsNumber;
    private String description;

    @Lob
    @Column(columnDefinition="CLOB")
    private String question;
    @Lob
    @Column(columnDefinition="CLOB")
    private String answer;

    public Question(
            Long id,
            QuestionType questionType,
            Double score,
            String name,
            Integer atemptsNumber,
            Integer number
    ) {
        this.id = id;
        this.questionType = questionType;
        this.score = score;
        this.name = name;
        this.atemptsNumber = atemptsNumber;
        this.number = number;
    }
}
