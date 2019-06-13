package kurlyk.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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

    private Boolean negativeScore;
    private Long decScore;
    private Double scoreMultiplier;
    private Duration interval;

    // Варианты
    private Integer variantsNumber;
    private Integer difficultyLevelsNumber;

    @Override
    public String toString() {
        return name;
    }
}
