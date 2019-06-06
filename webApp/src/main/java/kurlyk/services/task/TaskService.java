package kurlyk.services.task;

import kurlyk.models.Task;

import java.util.List;
import java.util.Optional;


public interface TaskService {

    Optional<Task> getTask(Long id);
    List<Task> getTasks(Long labWorkId);
    List<Task> getTasks();
    Long saveTask(Task task);
    void deleteTask(Long id);
}
