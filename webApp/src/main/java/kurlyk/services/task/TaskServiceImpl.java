package kurlyk.services.task;

import kurlyk.models.Task;
import kurlyk.repositories.TaskRepository;
import kurlyk.transfer.TaskDto;
import kurlyk.utils.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Override
    public void post(TaskDto taskDto) {
        Task task = Task.builder()
                .id(null)
                .name(taskDto.getName())
                .labNumber(taskDto.getLabNumber())
                .labType(taskDto.getLabType())
                .question(taskDto.getQuestion())
                .answer(taskDto.getAnswer())
                .build();
        taskRepository.save(task);
    }

    @Override
    public TaskDto getTaskByName(String name){
        return Converter.taskToTaskDto(
                taskRepository
                        .findOneByName(name)
                        .orElseThrow(() -> new IllegalArgumentException("Task not found"))
        );
    }

    @Override
    public List<TaskDto> getLab(Integer labNumber){
        return Converter.listToList(
                taskRepository.findAllByLabNumber(labNumber),
                Converter::taskToTaskDto
        );
    }
}
