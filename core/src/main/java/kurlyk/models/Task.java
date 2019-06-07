package kurlyk.models;


import kurlyk.WhenShowAnswer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.Duration;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private LabWork labWork;

    private Integer number;
    private String name;
    private Integer attemptsNumber;
    private Long defaultQuestionScore;

    @Enumerated(value = EnumType.STRING)
    private WhenShowAnswer whenShowAnswer;
    private Boolean negativeScore;
    private Long decScore;
    private Double scoreMultiplier;
    private Integer variantsNumber;
    private Boolean skipQuestion;
    private Duration interval;
    private Boolean isTest;
}
