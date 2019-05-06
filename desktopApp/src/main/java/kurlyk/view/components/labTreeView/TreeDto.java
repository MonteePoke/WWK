package kurlyk.view.components.labTreeView;

import kurlyk.models.LabWork;
import kurlyk.models.Question;
import kurlyk.models.Subject;
import kurlyk.models.Task;
import lombok.Data;

@Data
public class TreeDto {
    private TreeDtoType type;
    private Subject subject;
    private LabWork labWork;
    private Task task;
    private Question question;

    public TreeDto(Subject subject) {
        this.type = TreeDtoType.SUBJECT;
        this.subject = subject;
    }

    public TreeDto(LabWork labWork) {
        this.type = TreeDtoType.LAB_WORK;
        this.labWork = labWork;
    }

    public TreeDto(Task task) {
        this.type = TreeDtoType.TASK;
        this.task = task;
    }

    public TreeDto(Question question) {
        this.type = TreeDtoType.QUESTION;
        this.question = question;
    }

    public TreeDto(TreeDtoType type) {
        this.type = type;
    }
}
