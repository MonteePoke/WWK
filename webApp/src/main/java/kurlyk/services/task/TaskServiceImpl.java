package kurlyk.services.task;

import kurlyk.model.Task;
import kurlyk.repositories.QuestionRepository;
import kurlyk.repositories.TaskRepository;
import kurlyk.services.question.QuestionService;
import kurlyk.services.usverProgress.UsverProgressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UsverProgressService usverProgressService;

    @Autowired
    private QuestionService questionService;

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
        usverProgressService.deleteUsverProgressLabWorkByLabWorkId(id);
        questionService.getQuestions(id).forEach(
                question -> {
                    questionService.deleteQuestion(question.getId());
                });
        taskRepository.deleteById(id);
    }
}
