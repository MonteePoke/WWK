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
public class UserProgressLabWork {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;
    @ManyToOne
    private LabWork labWork;
    private Boolean isDeleted;
    private Long score;
    private LocalDateTime startTime;

    @OneToMany
    private Set<UserProgressTask> userProgressTasks;

    @OneToMany
    private Set<UserProgressLabWorkParameter> parameters;
}
