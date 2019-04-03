package kurlyk.repositories;


import kurlyk.models.TaskQuestion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskQuestionRepository extends JpaRepository<TaskQuestion, Long> {

    List<TaskQuestion> findByTaskId(Long id);
}
