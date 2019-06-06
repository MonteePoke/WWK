package kurlyk.services.question;

import kurlyk.models.Question;
import kurlyk.repositories.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionRepository questionRepository;


    @Override
    public Optional<Question> getQuestion(Long id) {
        return questionRepository.findOneById(id);
    }

    @Override
    public List<Question> getQuestions(Long taskId){
        return questionRepository.findByTaskId(taskId);
    }

    @Override
    public List<Question> getQuestions(int pageNumber, int contentSize){
        return questionRepository.findAll(PageRequest.of(pageNumber, contentSize)).getContent();
    }

    @Override
    public List<Question> getQuestionHeaders(Long taskId){
        return questionRepository.getQuestionHeadersByTaskId(taskId);
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
}
