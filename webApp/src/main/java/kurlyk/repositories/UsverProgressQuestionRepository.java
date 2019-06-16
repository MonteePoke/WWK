package kurlyk.repositories;


import kurlyk.model.UsverProgressQuestion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsverProgressQuestionRepository extends JpaRepository<UsverProgressQuestion, Long> {

    List<UsverProgressQuestion> findByQuestionId(Long questionId);
}
