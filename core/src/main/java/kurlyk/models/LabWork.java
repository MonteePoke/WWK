package kurlyk.models;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class LabWork {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer number;
    private String name;
    private Integer atemptsNumber;

    //Придумал Саша
    private Boolean interrupt;
    private Long defaultQuestionScore;
    private Long whenShowAnswer;
    private Long negativeScore;
    private Long decScore;

    @Override
    public String toString() {
        return name;
    }
}
