package kurlyk.repositories;


import kurlyk.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, Long> {

    Optional<Task> findOneById(Long id);

    List<Task> findByLabWorkId(Long labWorkId);

    @Query("SELECT t.id FROM Task as t WHERE t.labWork.id = ?1")
    List<Long> findOnlyIdByLabWorkId(Long labWorkId);

    @Query("SELECT t.id FROM Task as t WHERE t.labWork.id = ?1 and (t.variantsNumber = ?2 or t.number = 0) ORDER BY t.number")
    List<Long> findByLabWorkIdAndVariant(Long labWorkId, Integer variants);
}
