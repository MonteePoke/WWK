package kurlyk.services.userProgress;

import kurlyk.models.UserLabWorkAccess;
import kurlyk.models.UserProgressLabWork;
import kurlyk.transfer.UserLabWorkDto;

import java.util.List;
import java.util.Optional;


public interface UserProgressService {

    List<UserProgressLabWork> getUserProgress(UserLabWorkDto userLabWorkDto);
    Long saveUserProgress(UserProgressLabWork userProgressLabWork);
    void deleteUserProgress(Long id);

    Optional<UserLabWorkAccess> getUserLabWorkAccess(UserLabWorkDto userLabWorkDto);
    Long saveUserLabWorkAccess(UserLabWorkAccess userLabWorkAccess);
    void deleteUserLabWorkAccess(Long id);
}
