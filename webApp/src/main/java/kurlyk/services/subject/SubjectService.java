package kurlyk.services.subject;

import kurlyk.models.Subject;

import java.util.List;
import java.util.Optional;


public interface SubjectService {

    Optional<Subject> getSubject(Long id);
    List<Subject> getSubjects();
    void saveSubject(Subject subject);
    void deleteSubject(Long id);
}
