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
public class UsverProgressLabWork {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Usver usver;
    @ManyToOne
    private LabWork labWork;
    private Long startTime;
    private Long endTime;

//    @OneToMany(cascade = CascadeType.ALL)
//    private Set<UsverProgressTask> usverProgressTasks;


    @OneToMany(cascade = CascadeType.ALL)
    private Set<UsverProgressLabWorkParameter> parameters;
}
