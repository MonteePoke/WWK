package kurlyk.repositories;


import kurlyk.models.Question;
import kurlyk.models.TaskQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TaskQuestionRepository extends JpaRepository<TaskQuestion, Long> {

    List<TaskQuestion> findByTaskId(Long id);

    @Query("SELECT new TaskQuestion(" +
            "tq.id, " +
            "tq.number, " +
            "tq.task.id, " +
            "tq.task.name, " +
            "tq.question.id, " +
            "tq.question.name" +
            ") FROM TaskQuestion as tq")
    List<TaskQuestion> getTaskQuestionMatching();

    @Query("SELECT new Question(" +
            "tq.question.id, " +
            "tq.question.questionType, " +
            "tq.question.score, " +
            "tq.question.name, " +
            "tq.question.attemptsNumber, " +
            "tq.number" +
            ") FROM TaskQuestion as tq WHERE tq.task.id = ?1")
    List<Question> getQuestionHeadersByTaskId(Long id);

    @Query("SELECT new TaskQuestion(" +
            "tq.id, " +
            "tq.number, " +
            "tq.task.id, " +
            "tq.task.name, " +
            "tq.question.id, " +
            "tq.question.name" +
            ") FROM TaskQuestion as tq WHERE tq.task.id = ?1")
    List<TaskQuestion> getTaskQuestionMatchingByTaskId(Long id);

    Optional<TaskQuestion> findByTaskIdAndQuestionId(Long taskId, Long questionId);

    void deleteByTaskId(Long id);

    void deleteByQuestionId(Long id);
}
