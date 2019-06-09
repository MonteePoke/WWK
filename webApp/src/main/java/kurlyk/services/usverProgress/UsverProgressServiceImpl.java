package kurlyk.services.usverProgress;

import kurlyk.models.UsverLabWorkAccess;
import kurlyk.models.UsverProgressLabWork;
import kurlyk.repositories.UsverLabWorkAccessRepository;
import kurlyk.repositories.UsverProgressRepository;
import kurlyk.transfer.UsverLabWorkDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UsverProgressServiceImpl implements UsverProgressService {

    @Autowired
    private UsverProgressRepository usverProgressRepository;

    @Autowired
    private UsverLabWorkAccessRepository usverLabWorkAccessRepository;

    @Override
    public List<UsverProgressLabWork> getUsverProgress(UsverLabWorkDto usverLabWorkDto) {
        if (usverLabWorkDto.getUsverId() == null){
            return new ArrayList<>();
        } else if (usverLabWorkDto.getLabWorkId() == null){
            return usverProgressRepository.findByUsverId(usverLabWorkDto.getUsverId());
        } else {
            return usverProgressRepository.findByUsverIdAndLabWorkId(
                    usverLabWorkDto.getUsverId(),
                    usverLabWorkDto.getLabWorkId()
            );
        }
    }

    @Override
    public Long saveUsverProgress(UsverProgressLabWork usverProgressLabWork) {
        return usverProgressRepository.save(usverProgressLabWork).getId();
    }

    @Override
    public void deleteUsverProgress(Long id) {
        usverProgressRepository.deleteById(id);
    }



    @Override
    public Optional<UsverLabWorkAccess> getUsverLabWorkAccess(UsverLabWorkDto usverLabWorkDto) {
        return usverLabWorkAccessRepository.findOneByUsverIdAndLabWorkId(
                usverLabWorkDto.getUsverId(),
                usverLabWorkDto.getLabWorkId()
        );
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
