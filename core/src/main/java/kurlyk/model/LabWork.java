package kurlyk.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;


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
    private Integer attemptsNumber;
    private Boolean interrupt;
    private Long defaultQuestionScore;

    private Boolean negativeScore;
    private Long decScore;
    private Double scoreMultiplier;

    //в минутах
    private Long interval;

    @OneToMany
    private Set<ParameterValue> parameterValues;

    // Варианты
    private Integer variantsNumber;
    private Integer difficultyLevelsNumber;

    @Override
    public String toString() {
        return name;
    }
}
