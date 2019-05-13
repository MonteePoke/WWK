package kurlyk.repositories;


import kurlyk.models.LabWorkTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LabWorkTaskRepository extends JpaRepository<LabWorkTask, Long> {

    List<LabWorkTask> findByLabWorkId(Long id);
    @Query("SELECT new LabWorkTask(lt.id, lt.labWork.id, lt.labWork.name, lt.task.id, lt.task.name) FROM LabWorkTask as lt")
    List<LabWorkTask> getLabWorkTaskMatching();
    @Query("SELECT new LabWorkTask(lt.id, lt.labWork.id, lt.labWork.name, lt.task.id, lt.task.name) FROM LabWorkTask as lt WHERE lt.labWork.id = ?1")
    List<LabWorkTask> getLabWorkTaskMatchingByLabWorkId(Long id);

    void deleteByLabWorkId(Long id);
    void deleteByTaskId(Long id);
}
