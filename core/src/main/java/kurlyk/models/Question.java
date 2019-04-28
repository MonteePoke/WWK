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
    private Integer atemptsNumber;
    private Double score;
    private String description;

    @Lob
    @Column(columnDefinition="CLOB")
    private String question;
    @Lob
    @Column(columnDefinition="CLOB")
    private String answer;

    public Question(Long id, QuestionType questionType, String name) {
        this.id = id;
        this.questionType = questionType;
        this.name = name;
    }
}
