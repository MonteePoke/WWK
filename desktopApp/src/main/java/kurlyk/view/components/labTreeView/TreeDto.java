package kurlyk.view.components.labTreeView;

import kurlyk.WorkEntityType;
import kurlyk.models.LabWork;
import kurlyk.models.Question;
import kurlyk.models.Subject;
import kurlyk.models.Task;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class TreeDto {
    private WorkEntityType type;
    private Subject subject;
    private LabWork labWork;
    private Task task;
    private Question question;

    public TreeDto(Subject subject) {
        this.type = WorkEntityType.SUBJECT;
        this.subject = subject;
    }

    public TreeDto(LabWork labWork) {
        this.type = WorkEntityType.LAB_WORK;
        this.labWork = labWork;
    }

    public TreeDto(Task task) {
        this.type = WorkEntityType.TASK;
        this.task = task;
    }

    public TreeDto(Question question) {
        this.type = WorkEntityType.QUESTION;
        this.question = question;
    }

    public TreeDto(WorkEntityType type) {
        this.type = type;
    }



    //Создание CustomTreeItem
    public static CustomTreeItem itemOf(Subject subject){
        return new CustomTreeItem(null, new TreeDto(subject));
    }

    public static CustomTreeItem itemOf(CustomTreeItem itemParent, LabWork labWork){
        return new CustomTreeItem(itemParent, new TreeDto(labWork));
    }

    public static CustomTreeItem itemOf(CustomTreeItem itemParent, Task task){
        return new CustomTreeItem(itemParent, new TreeDto(task));
    }

    public static CustomTreeItem itemOf(CustomTreeItem itemParent, Question question){
        return new CustomTreeItem(itemParent, new TreeDto(question));
    }

    public static List<CustomTreeItem> itemsOfSubjects(List<Subject> subjects){
        return subjects
                .stream()
                .map(TreeDto::itemOf)
                .collect(Collectors.toList());
    }

    public static List<CustomTreeItem> itemsOfLabWorks(CustomTreeItem itemParent, List<LabWork> labWorks){
        return labWorks
                .stream()
                .map(labWork -> itemOf(itemParent, labWork))
                .collect(Collectors.toList());
    }

    public static List<CustomTreeItem> itemsOfTasks(CustomTreeItem itemParent, List<Task> tasks){
        return tasks
                .stream()
                .map(task -> itemOf(itemParent, task))
                .collect(Collectors.toList());
    }

    public static List<CustomTreeItem> itemsOfQuestions(CustomTreeItem itemParent, List<Question> questions){
        return questions
                .stream()
                .map(question -> itemOf(itemParent, question))
                .collect(Collectors.toList());
    }
}
