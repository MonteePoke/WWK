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
public class LabWorkTask {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer number;

    @ManyToOne
    private LabWork labWork;
    @ManyToOne
    private Task task;

    public LabWorkTask(Long id, Integer number, Long labWorkId, String labWorkName, Long taskId, String taskName) {
        this.id = id;
        this.number = number;
        this.labWork = LabWork.builder().id(labWorkId).name(labWorkName).build();
        this.task = Task.builder().id(taskId).name(taskName).build();
    }
}
