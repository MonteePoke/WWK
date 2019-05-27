package kurlyk.services.question;

import kurlyk.models.Question;
import kurlyk.models.TaskQuestion;
import kurlyk.repositories.QuestionRepository;
import kurlyk.repositories.TaskQuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private TaskQuestionRepository taskQuestionRepository;


    @Override
    public Optional<Question> getQuestion(Long id) {
        return questionRepository.findOneById(id);
    }

    @Override
    public List<Question> getQuestions(Long taskId){
        return taskQuestionRepository.findByTaskId(taskId)
                        .stream()
                        .map(TaskQuestion::getQuestion)
                        .collect(Collectors.toList());
    }

    @Override
    public List<Question> getQuestionHeaders(Long taskId){
        return taskQuestionRepository.getQuestionHeadersByTaskId(taskId);
    }

    @Override
    public List<Question> getQuestionHeaders(){
        return questionRepository.getQuestionHeaders();
    }

    @Override
    public Long saveQuestion(Question question){
        return questionRepository.save(question).getId();
    }

    @Override
    public void deleteQuestion(Long id) {
        questionRepository.deleteById(id);
    }

    @Override
    public List<Question> getQuestions(int pageNumber, int contentSize){
        return questionRepository.findAll(PageRequest.of(pageNumber, contentSize)).getContent();
    }
}
