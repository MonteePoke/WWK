package kurlyk.repositories;


import kurlyk.models.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface QuestionRepository extends JpaRepository<Question, Long> {

    Optional<Question> findOneById(Long id);

    List<Question> findByTaskId(Long taskId);

    @Query("SELECT new Question(q.id, q.task, q.questionType, q.number, q.name, q.attemptsNumber, q.interrupt, q.score, q.whenShowAnswer, q.negativeScore, q.decScore, q.scoreMultiplier, q.variantsNumber, q.skipQuestion, q.interval) FROM Question as q")
    List<Question> getQuestionHeaders();

    @Query("SELECT new Question(q.id, q.task, q.questionType, q.number, q.name, q.attemptsNumber, q.interrupt, q.score, q.whenShowAnswer, q.negativeScore, q.decScore, q.scoreMultiplier, q.variantsNumber, q.skipQuestion, q.interval) FROM Question as q WHERE q.task.id = ?1")
    List<Question> getQuestionHeadersByTaskId(Long taskId);
}
