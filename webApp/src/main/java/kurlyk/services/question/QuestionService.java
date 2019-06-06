package kurlyk.services.question;

import kurlyk.models.Question;

import java.util.List;
import java.util.Optional;


public interface QuestionService {

    Optional<Question> getQuestion(Long id);
    List<Question> getQuestions(Long taskId);
    List<Question> getQuestions(int pageNumber, int contentSize);
    List<Question> getQuestionHeaders(Long taskId);
    List<Question> getQuestionHeaders();
    Long saveQuestion(Question question);
    void deleteQuestion(Long id);
}
