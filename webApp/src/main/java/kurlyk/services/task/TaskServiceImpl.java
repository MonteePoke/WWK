package kurlyk.services.task;

import kurlyk.models.Task;
import kurlyk.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Override
    public Optional<Task> getTask(Long id) {
        return taskRepository.findOneById(id);
    }

    @Override
    public List<Task> getTasks(Long labWorkId) {
        return taskRepository.findByLabWorkId(labWorkId);
    }

    @Override
    public List<Long> getTaskIds(Long labWorkId) {
        return taskRepository.findOnlyIdByLabWorkId(labWorkId);
    }

    @Override
    public List<Task> getTasks() {
        return taskRepository.findAll();
    }

    @Override
    public Long saveTask(Task task) {
        return taskRepository.save(task).getId();
    }

    @Override
    public void deleteTask(Long id){
        taskRepository.deleteById(id);
    }
}
