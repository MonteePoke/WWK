package kurlyk.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class UsverProgressLabWork {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Usver usver;
    @ManyToOne
    private LabWork labWork;
    private Long score;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    @OneToMany
    private Set<UsverProgressTask> usverProgressTasks;

    @OneToMany
    private Set<UsverProgressLabWorkParameter> parameters;
}
