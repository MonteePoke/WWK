package kurlyk.services.task;

import kurlyk.models.LabWorkTask;
import kurlyk.models.Task;
import kurlyk.models.TaskQuestion;
import kurlyk.repositories.LabWorkTaskRepository;
import kurlyk.repositories.TaskQuestionRepository;
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

    @Autowired
    private TaskQuestionRepository taskQuestionRepository;


    @Override
    public Optional<Task> getTask(Long id) {
        return taskRepository.findOneById(id);
    }

    @Override
    public List<Task> getTasks(Long labWorkId) {
        return labWorkTaskRepository.findByLabWorkId(labWorkId)
                        .stream()
                        .peek(labWorkTask -> labWorkTask.getTask().setNumber(labWorkTask.getNumber()))
                        .map(LabWorkTask::getTask)
                        .collect(Collectors.toList());
    }

    @Override
    public List<Task> getTasks() {
        return taskRepository.findAll();
    }

    @Override
    public List<TaskQuestion> getTaskQuestionMatching() {
        return taskQuestionRepository.getTaskQuestionMatching();
    }

    @Override
    public List<TaskQuestion> getTaskQuestionMatching(Long taskId){
        return taskQuestionRepository.getTaskQuestionMatchingByTaskId(taskId);
    }

    @Override
    public Long saveTaskQuestionMatching(TaskQuestion taskQuestion){
        Optional<TaskQuestion> taskQuestionOptional = taskQuestionRepository.findByTaskIdAndQuestionId(
                taskQuestion.getTask().getId(),
                taskQuestion.getQuestion().getId()
        );
        taskQuestionOptional.ifPresent(question -> taskQuestion.setId(question.getId()));
        return taskQuestionRepository.save(taskQuestion).getId();
    }

    @Override
    public Long saveTask(Task task) {
        return taskRepository.save(task).getId();
    }

    @Override
    public void deleteTask(Long id){
        taskRepository.deleteById(id);
    }

    @Override
    public void deleteTaskQuestionMatching(Long id){
        taskQuestionRepository.deleteById(id);
    }

    @Override
    public void deleteTaskQuestionMatchingByTaskId(Long id){
        taskQuestionRepository.deleteByTaskId(id);
    }

    @Override
    public void deleteTaskQuestionMatchingByQuestionId(Long id){
        taskQuestionRepository.deleteByQuestionId(id);
    }
}
