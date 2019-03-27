package kurlyk.services.task;


import kurlyk.transfer.QuestionDto;

import java.util.List;

public interface TaskService {
    void post(QuestionDto questionDto);
    QuestionDto getTaskByName(String name);
    List<QuestionDto> getLab(Integer labNumber);
}
