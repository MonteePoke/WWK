package kurlyk.services.subject;

import kurlyk.model.Subject;
import kurlyk.repositories.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubjectServiceImpl implements SubjectService {

    @Autowired
    private SubjectRepository subjectRepository;

    @Override
    public Optional<Subject> getSubject(Long id) {
        return subjectRepository.findById(id);
    }

    @Override
    public List<Subject> getSubjects() {
        return subjectRepository.findAll();
    }

    @Override
    public Long saveSubject(Subject subject) {
        return subjectRepository.save(subject).getId();
    }

    @Override
    public void deleteSubject(Long id) {
        subjectRepository.deleteById(id);
    }
}
