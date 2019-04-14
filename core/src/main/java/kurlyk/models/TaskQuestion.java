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
public class TaskQuestion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Task task;
    @ManyToOne
    private Question question;

    public TaskQuestion(Long id, Long taskId, String taskName, Long questionId, String questionName) {
        this.id = id;
        this.task = Task.builder().id(taskId).name(taskName).build();
        this.question = Question.builder().id(questionId).name(questionName).build();
    }
}
