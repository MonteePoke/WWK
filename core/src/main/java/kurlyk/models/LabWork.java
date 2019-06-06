package kurlyk.models;


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
public class LabWork {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer number;
    private String name;
    private Integer attemptsNumber;
    private Boolean interrupt;
    private Long defaultQuestionScore;

    @Enumerated(value = EnumType.STRING)
    private WhenShowAnswer whenShowAnswer;
    private Boolean negativeScore;
    private Long decScore;
    private Double scoreMultiplier;
    private Integer variantsNumber;
    private Boolean skipQuestion;
    private Duration interval;

    // Варианты
    private Integer variantNumber;
    private Integer difficultyLevelsNumber;

    @Override
    public String toString() {
        return name;
    }
}
