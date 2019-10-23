package kurlyk.services.usverProgress;

import kurlyk.model.UsverLabWorkAccess;
import kurlyk.model.UsverProgressLabWork;
import kurlyk.model.UsverProgressTask;
import kurlyk.repositories.UsverLabWorkAccessRepository;
import kurlyk.repositories.UsverProgressLabWorkRepository;
import kurlyk.transfer.UsverLabWorkDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UsverProgressServiceImpl implements UsverProgressService {

    @Autowired
    private UsverProgressLabWorkRepository usverProgressLabWorkRepository;

    @Autowired
    private UsverLabWorkAccessRepository usverLabWorkAccessRepository;

    @Override
    public Long saveUsverProgress(UsverProgressLabWork usverProgressLabWork) {
        getUsverProgress(usverProgressLabWork.getUsver().getId(), usverProgressLabWork.getLabWork().getId())
                .ifPresent(progressLabWork -> usverProgressLabWork.setId(progressLabWork.getId()));
        return usverProgressLabWorkRepository.save(usverProgressLabWork).getId();
    }

    @Override
    public Optional<UsverProgressLabWork> getUsverProgress(Long usverId, Long labWorkId) {
        return usverProgressLabWorkRepository.findOneByUsverIdAndLabWorkId(usverId, labWorkId);
    }

    @Override
    public void deleteUsverProgress(Long id) {
        usverProgressLabWorkRepository.deleteById(id);
    }

    @Override
    public Optional<UsverLabWorkAccess> getUsverLabWorkAccess(UsverLabWorkDto usverLabWorkDto) {
        return usverLabWorkAccessRepository.findOneByLabWorkIdAndUsverId(
                usverLabWorkDto.getUsverId(),
                usverLabWorkDto.getLabWorkId()
        );
    }

    public void deleteUsverProgressLabWorkByLabWorkId(Long labWorkId) {
        List<UsverProgressLabWork> usverProgressLabWorks = usverProgressLabWorkRepository.findByLabWorkId(labWorkId);
        List<UsverProgressTask> usverProgressTasks = new ArrayList<>();

        usverProgressLabWorks.forEach(
                usverProgressLabWork -> {
                    usverProgressTasks.addAll(usverProgressTaskRepository.findByUsverProgressLabWorkId(usverProgressLabWork.getId()));
                }
        );
        usverProgressTasks.forEach(
                usverProgressTask -> usverProgressQuestionRepository.deleteAll(usverProgressQuestionRepository.findByUsverProgressTaskId(usverProgressTask.getId()))
        );

        usverProgressLabWorkRepository.deleteAll(usverProgressLabWorks);
        usverProgressTaskRepository.deleteAll(usverProgressTasks);
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
