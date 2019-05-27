package kurlyk.services.task;

import kurlyk.models.Task;
import kurlyk.models.TaskQuestion;

import java.util.List;
import java.util.Optional;


public interface TaskService {

    Optional<Task> getTask(Long id);
    List<Task> getTasks(Long labWorkId);
    List<Task> getTasks();
    List<TaskQuestion> getTaskQuestionMatching();
    List<TaskQuestion> getTaskQuestionMatching(Long taskId);
    Long saveTaskQuestionMatching(TaskQuestion taskQuestion);
    Long saveTask(Task task);
    void deleteTask(Long id);
    void deleteTaskQuestionMatching(Long id);
    void deleteTaskQuestionMatchingByTaskId(Long id);
    void deleteTaskQuestionMatchingByQuestionId(Long id);
}
