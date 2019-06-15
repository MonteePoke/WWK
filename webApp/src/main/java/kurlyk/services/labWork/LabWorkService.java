package kurlyk.services.labWork;

import kurlyk.model.LabWork;
import kurlyk.model.UsverLabWorkAccess;

import java.util.List;
import java.util.Optional;


public interface LabWorkService {

    List<LabWork> getLabWorks();
    Optional<LabWork> getLabWork(Long id);
    Long saveLabWork(LabWork labWork);
    void deleteLabWork(Long id);

    Optional<UsverLabWorkAccess> getUsverLabWorkAccess(Long labWorkId, Long usverId);
    Long saveUsverLabWorkAccess(UsverLabWorkAccess usverLabWorkAccess);
    void deleteUsverLabWorkAccess(Long id);
}
