package kurlyk.repositories;


import kurlyk.model.UsverProgressTask;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsverProgressTaskRepository extends JpaRepository<UsverProgressTask, Long> {

    List<UsverProgressTask> findByTaskId(Long taskId);
    void deleteByTaskId(Long taskId);

    List<UsverProgressTask> findByUsverProgressLabWorkId(Long usverProgressLabWorkId);
    void deleteByUsverProgressLabWorkId(Long usverProgressLabWorkId);
}
