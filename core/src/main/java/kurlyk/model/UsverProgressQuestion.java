package kurlyk.model;

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
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"QUESTION_ID", "USVER_PROGRESS_LAB_WORK_ID"})})
public class UsverProgressQuestion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Question question;

    @ManyToOne
    private UsverProgressLabWork usverProgressLabWork;

    private Long score;
    private Integer attemptsNumber;

    private Boolean responseReceived;
}
