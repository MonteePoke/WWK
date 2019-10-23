package kurlyk.services.labWork;

import kurlyk.model.LabWork;
import kurlyk.model.UsverLabWorkAccess;
import kurlyk.repositories.LabWorkRepository;
import kurlyk.repositories.UsverLabWorkAccessRepository;
import kurlyk.services.question.QuestionService;
import kurlyk.services.task.TaskService;
import kurlyk.services.usverProgress.UsverProgressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class LabWorkServiceImpl implements LabWorkService {

    @Autowired
    private LabWorkRepository labWorkRepository;

    @Autowired
    private UsverLabWorkAccessRepository usverLabWorkAccessRepository;

    @Autowired
    private UsverProgressService usverProgressService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private QuestionService questionService;

    @Override
    public Optional<LabWork> getLabWork(Long id) {
        return labWorkRepository.findById(id);
    }

    @Override
    public List<LabWork> getLabWorks() {
        return labWorkRepository.findAll();
    }

    @Override
    public Long saveLabWork(LabWork labWork) {
        return labWorkRepository.save(labWork).getId();
    }

    @Override
    @Transactional
    public void deleteLabWork(Long id){
        usverProgressService.deleteUsverProgressLabWorkByLabWorkId(id);
        taskService.getTasks(id).forEach(
                task -> {
                    questionService.getQuestions(task.getId()).forEach(
                            question -> {
                                questionService.deleteQuestion(question.getId());
                            });
                    taskService.deleteTask(task.getId());
                }
        );
        labWorkRepository.deleteById(id);
    }

    @Override
    public Optional<UsverLabWorkAccess> getUsverLabWorkAccess(Long labWorkId, Long usverId) {
        return usverLabWorkAccessRepository.findOneByLabWorkIdAndUsverId(labWorkId, usverId);
    }

    @Override
    public Long saveUsverLabWorkAccess(UsverLabWorkAccess usverLabWorkAccess) {
        return usverLabWorkAccessRepository.save(usverLabWorkAccess).getId();
    }

    @Override
    public void deleteUsverLabWorkAccess(Long id) {
        usverLabWorkAccessRepository.deleteById(id);
    }
}
