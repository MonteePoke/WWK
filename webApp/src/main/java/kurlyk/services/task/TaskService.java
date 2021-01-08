package kurlyk.services.task;

import kurlyk.model.Task;

import java.util.List;
import java.util.Optional;


public interface TaskService {

    Optional<Task> getTask(Long id);
    List<Task> getTasks(Long labWorkId);
    List<Long> getTaskIds(Long labWorkId, Integer variant);
    List<Task> getTasks();
    Long saveTask(Task task);
    void deleteTask(Long id);
}
