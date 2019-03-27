package kurlyk.models;

import kurlyk.QuestionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;


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
    @Column(nullable = false)
    private QuestionType questionType;

    @Lob
    @Column(columnDefinition="CLOB")
    private String question;
    @Lob
    @Column(columnDefinition="CLOB")
    private String answer;

    private Integer score;
    private String description;

    @ManyToMany
    private List<Competence> competences;
}
