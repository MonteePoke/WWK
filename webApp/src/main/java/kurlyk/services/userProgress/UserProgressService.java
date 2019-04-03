package kurlyk.services.userProgress;

import kurlyk.models.UserProgress;
import kurlyk.transfer.UserProgressDto;

import java.util.List;


public interface UserProgressService {

    UserProgress getOneUserProgress(Long id);
    List<UserProgressDto> getUserProgress(UserProgressDto userProgressDto);
    List<UserProgress> getFullUserProgress(UserProgressDto userProgressDto);
    void saveUserProgress(UserProgress userProgress);
}
