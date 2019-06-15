package kurlyk.services.labWork;

import kurlyk.model.LabWork;
import kurlyk.model.UsverLabWorkAccess;
import kurlyk.repositories.LabWorkRepository;
import kurlyk.repositories.UsverLabWorkAccessRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LabWorkServiceImpl implements LabWorkService {

    @Autowired
    private LabWorkRepository labWorkRepository;

    @Autowired
    private UsverLabWorkAccessRepository usverLabWorkAccessRepository;

    @Override
    public Optional<LabWork> getLabWork(Long id){
        return labWorkRepository.findById(id);
    }

    @Override
    public List<LabWork> getLabWorks() {
        return labWorkRepository.findAll();
    }

    @Override
    public Long saveLabWork(LabWork labWork){
        return labWorkRepository.save(labWork).getId();
    }

    @Override
    public void deleteLabWork(Long id){
        labWorkRepository.deleteById(id);
    }

    @Override
    public Optional<UsverLabWorkAccess> getUsverLabWorkAccess(Long labWorkId, Long usverId) {
        return usverLabWorkAccessRepository.findOneByLabWorkIdAndUsverId(labWorkId, usverId);
    }

    @Override
    public Long saveUsverLabWorkAccess(UsverLabWorkAccess usverLabWorkAccess) {
        return usverLabWorkAccessRepository.save(usverLabWorkAccess).getId();
    }

    @Override
    public void deleteUsverLabWorkAccess(Long id) {
        usverLabWorkAccessRepository.deleteById(id);
    }
}
