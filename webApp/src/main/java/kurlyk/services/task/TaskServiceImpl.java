package kurlyk.services.task;

import kurlyk.repositories.TaskRepository;
import kurlyk.transfer.QuestionDto;
import kurlyk.utils.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Override
    public void post(QuestionDto questionDto) {
        taskRepository.save(Converter.questionConverter(questionDto));
    }

    @Override
    public QuestionDto getTaskByName(String name){
        return Converter.questionConverter(
                taskRepository
                        .findOneByName(name)
                        .orElseThrow(() -> new IllegalArgumentException("Question not found"))
        );
    }

    @Override
    public List<QuestionDto> getLab(Integer labNumber){
        return Converter.listToList(
                taskRepository.findAllByLabNumber(labNumber),
                Converter::questionConverter
        );
    }
}
