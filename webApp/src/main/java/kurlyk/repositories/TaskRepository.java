package kurlyk.repositories;


import kurlyk.models.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends JpaRepository<Question, Long> {

    Optional<Question> findOneByName(String name);
    List<Question> findAllByLabNumber(Integer labNumber);
}
