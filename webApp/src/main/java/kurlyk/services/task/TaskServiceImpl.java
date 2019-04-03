package kurlyk.services.task;

import kurlyk.models.LabWorkTask;
import kurlyk.models.Task;
import kurlyk.repositories.LabWorkTaskRepository;
import kurlyk.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private LabWorkTaskRepository labWorkTaskRepository;


    @Override
    public Optional<Task> getTask(Long id) {
        return taskRepository.findOneById(id);
    }

    @Override
    public List<Task> getTasks(Long labWorkId) {
        return labWorkTaskRepository.findByLabWorkId(labWorkId)
                        .stream()
                        .map(LabWorkTask::getTask)
                        .collect(Collectors.toList());
    }
}
