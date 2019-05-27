package kurlyk.services.labWork;

import kurlyk.models.LabWork;
import kurlyk.models.LabWorkTask;
import kurlyk.repositories.LabWorkRepository;
import kurlyk.repositories.LabWorkTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LabWorkServiceImpl implements LabWorkService {

    @Autowired
    private LabWorkRepository labWorkRepository;

    @Autowired
    private LabWorkTaskRepository labWorkTaskRepository;

    @Override
    public Optional<LabWork> getLabWork(Long id){
        return labWorkRepository.findById(id);
    }

    @Override
    public List<LabWork> getLabWorks() {
        return labWorkRepository.findAll();
    }

    @Override
    public List<LabWorkTask> getLabWorkTaskMatching() {
        return labWorkTaskRepository.getLabWorkTaskMatching();
    }

    @Override
    public List<LabWorkTask> getLabWorkTaskMatching(Long labWorkId){
        return labWorkTaskRepository.getLabWorkTaskMatchingByLabWorkId(labWorkId);
    }

    @Override
    public Long saveLabWorkTaskMatching(LabWorkTask labWorkTask){
        return labWorkTaskRepository.save(labWorkTask).getId();
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
    public void deleteLabWorkTaskMatching(Long id){
        labWorkTaskRepository.deleteById(id);
    }

    @Override
    public void deleteLabWorkTaskMatchingByLabWorkId(Long id){
        labWorkTaskRepository.deleteByLabWorkId(id);
    }

    @Override
    public void deleteLabWorkTaskMatchingByTaskId(Long id){
        labWorkTaskRepository.deleteByTaskId(id);
    }
}
