package kurlyk.repositories;


import kurlyk.models.LabWorkTask;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LabWorkTaskRepository extends JpaRepository<LabWorkTask, Long> {

    List<LabWorkTask> findByLabWorkId(Long id);
}
