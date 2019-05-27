package kurlyk.services.labWork;

import kurlyk.models.LabWork;
import kurlyk.models.LabWorkTask;

import java.util.List;
import java.util.Optional;


public interface LabWorkService {

    List<LabWork> getLabWorks();
    Optional<LabWork> getLabWork(Long id);
    List<LabWorkTask> getLabWorkTaskMatching();
    List<LabWorkTask> getLabWorkTaskMatching(Long labWorkId);
    Long saveLabWorkTaskMatching(LabWorkTask labWorkTask);
    Long saveLabWork(LabWork labWork);
    void deleteLabWork(Long id);
    void deleteLabWorkTaskMatching(Long id);
    void deleteLabWorkTaskMatchingByLabWorkId(Long id);
    void deleteLabWorkTaskMatchingByTaskId(Long id);
}
