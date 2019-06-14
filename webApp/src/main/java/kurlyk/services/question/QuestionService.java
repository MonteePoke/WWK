package kurlyk.services.question;

import kurlyk.model.Question;
import kurlyk.transfer.QuestionForTableDto;

import java.util.List;
import java.util.Optional;


public interface QuestionService {

    Optional<Question> getQuestion(Long id);
    Optional<Question> getQuestionForExecute(Long id);
    List<Question> getQuestions(Long taskId);
    List<Question> getQuestions(int pageNumber, int contentSize);
    List<Question> getQuestionHeaders(Long taskId);
    List<Question> getQuestionIdAndNumbers(Long taskId);
    List<Question> getQuestionHeaders();
    Long updateQuestionHeader(Question question);
    Long saveQuestion(Question question);
    void deleteQuestion(Long id);
    List<QuestionForTableDto> getQuestionsForTable(Integer pageNumber, Integer contentSize);
}
