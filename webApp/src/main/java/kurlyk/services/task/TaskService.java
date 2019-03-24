package kurlyk.services.task;


import kurlyk.transfer.TaskDto;

import java.util.List;

public interface TaskService {
    void post(TaskDto taskDto);
    TaskDto getTaskByName(String name);
    List<TaskDto> getLab(Integer labNumber);
}
