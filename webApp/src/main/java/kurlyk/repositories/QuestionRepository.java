package kurlyk.repositories;


import kurlyk.models.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface QuestionRepository extends JpaRepository<Question, Long> {

    Optional<Question> findOneById(Long id);

    @Query("SELECT new Question(" +
            "q.id, " +
            "q.questionType, " +
            "q.score, " +
            "q.name, " +
            "q.atemptsNumber, " +
            "q.number" +
            ") FROM Question as q")
    List<Question> getQuestionHeaders();
}
