package kurlyk.services.labWork;

import kurlyk.models.LabWork;
import kurlyk.repositories.LabWorkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LabWorkServiceImpl implements LabWorkService {

    @Autowired
    private LabWorkRepository labWorkRepository;

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
}
