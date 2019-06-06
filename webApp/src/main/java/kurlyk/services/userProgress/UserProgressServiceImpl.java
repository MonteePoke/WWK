package kurlyk.services.userProgress;

import kurlyk.models.UserLabWorkAccess;
import kurlyk.models.UserProgressLabWork;
import kurlyk.repositories.UserLabWorkAccessRepository;
import kurlyk.repositories.UserProgressRepository;
import kurlyk.transfer.UserLabWorkDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserProgressServiceImpl implements UserProgressService {

    @Autowired
    private UserProgressRepository userProgressRepository;

    @Autowired
    private UserLabWorkAccessRepository userLabWorkAccessRepository;

    @Override
    public List<UserProgressLabWork> getUserProgress(UserLabWorkDto userLabWorkDto) {
        if (userLabWorkDto.getUserId() == null){
            return new ArrayList<>();
        } else if (userLabWorkDto.getLabWorkId() == null){
            return userProgressRepository.findByUserId(userLabWorkDto.getUserId());
        } else {
            return userProgressRepository.findByUserIdAndLabWorkId(
                    userLabWorkDto.getUserId(),
                    userLabWorkDto.getLabWorkId()
            );
        }
    }

    @Override
    public Long saveUserProgress(UserProgressLabWork userProgressLabWork) {
        return userProgressRepository.save(userProgressLabWork).getId();
    }

    @Override
    public void deleteUserProgress(Long id) {
        userProgressRepository.deleteById(id);
    }



    @Override
    public Optional<UserLabWorkAccess> getUserLabWorkAccess(UserLabWorkDto userLabWorkDto) {
        return userLabWorkAccessRepository.findOneByUserIdAndLabWorkId(
                userLabWorkDto.getUserId(),
                userLabWorkDto.getLabWorkId()
        );
    }

    @Override
    public Long saveUserLabWorkAccess(UserLabWorkAccess userLabWorkAccess) {
        return userLabWorkAccessRepository.save(userLabWorkAccess).getId();
    }

    @Override
    public void deleteUserLabWorkAccess(Long id) {
        userLabWorkAccessRepository.deleteById(id);
    }
}
