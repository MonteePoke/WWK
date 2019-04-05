package kurlyk.models;


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
public class UserProgress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Question question;
    @ManyToOne
    private Task task;
    @ManyToOne
    private LabWork labWork;

    @ManyToOne
    private User user;
    private Double score;
    private Integer errorsNumber;
}
