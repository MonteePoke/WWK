package kurlyk.repositories;


import kurlyk.models.Question;
import kurlyk.transfer.QuestionForTableDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface QuestionRepository extends JpaRepository<Question, Long> {

    Optional<Question> findOneById(Long id);

    List<Question> findByTaskId(Long taskId);

    @Query("SELECT new Question(q.id, q.task, q.questionType, q.number, q.name, q.attemptsNumber, q.score, q.negativeScore, q.decScore, q.skipQuestion, q.interval) FROM Question as q")
    List<Question> getQuestionHeaders();

    @Query("SELECT new Question(q.id, q.task, q.questionType, q.number, q.name, q.attemptsNumber, q.score, q.negativeScore, q.decScore, q.skipQuestion, q.interval) FROM Question as q WHERE q.task.id = ?1")
    List<Question> getQuestionHeadersByTaskId(Long taskId);

    @Query("SELECT new Question(q.id, q.number, q.task.id, q.task.number) FROM Question as q WHERE q.task.id = ?1")
    List<Question> getQuestionIdAndNumbers(Long taskId);


    @Query(value = "SELECT new kurlyk.transfer.QuestionForTableDto(q.id, q.number, q.name, q.task.id, q.task.number, q.task.name, q.task.labWork.id, q.task.labWork.number, q.task.labWork.name) FROM Question as q")
    List<QuestionForTableDto> findAllForTable();

    @Query(value = "SELECT new kurlyk.transfer.QuestionForTableDto(q.id, q.number, q.name, q.task.id, q.task.number, q.task.name, q.task.labWork.id, q.task.labWork.number, q.task.labWork.name) FROM Question as q",
            countQuery = "SELECT count(new kurlyk.transfer.QuestionForTableDto(q.id, q.number, q.name, q.task.id, q.task.number, q.task.name, q.task.labWork.id, q.task.labWork.number, q.task.labWork.name)) FROM Question as q",
            nativeQuery = true)
    Page<QuestionForTableDto> findAllForTable(Pageable pageable);
}
