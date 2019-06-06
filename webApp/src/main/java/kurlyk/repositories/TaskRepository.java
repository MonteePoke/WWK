package kurlyk.repositories;


import kurlyk.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, Long> {

    Optional<Task> findOneById(Long id);

    List<Task> findByLabWorkId(Long labWorkId);
}
