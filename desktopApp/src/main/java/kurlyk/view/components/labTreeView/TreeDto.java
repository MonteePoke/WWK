package kurlyk.view.components.labTreeView;

import kurlyk.models.LabWork;
import kurlyk.models.Question;
import kurlyk.models.Task;
import lombok.Data;

@Data
public class TreeDto {
    private Long id;
    private TreeDtoType type;
    private String name;

    public TreeDto(LabWork labWork) {
        this.id = labWork.getId();
        this.type = TreeDtoType.LAB_WORK;
        this.name = labWork.getName();
    }

    public TreeDto(Task task) {
        this.id = task.getId();
        this.type = TreeDtoType.TASK;
        this.name = task.getNumber().toString();
    }

    public TreeDto(Question question) {
        this.id = question.getId();
        this.type = TreeDtoType.QUESTION;
        this.name = question.getNumber().toString();
    }

    public TreeDto(Long id, TreeDtoType type, String name) {
        this.id = id;
        this.type = type;
        this.name = name;
    }
}
