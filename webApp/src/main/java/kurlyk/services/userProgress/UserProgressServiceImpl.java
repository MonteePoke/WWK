package kurlyk.services.userProgress;

import kurlyk.common.Converter;
import kurlyk.models.UserProgress;
import kurlyk.repositories.UserProgressRepository;
import kurlyk.transfer.UserProgressDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserProgressServiceImpl implements UserProgressService {

    @Autowired
    private UserProgressRepository userProgressRepository;

    @Override
    public UserProgress getOneUserProgress(Long id) {
        return userProgressRepository.getOne(id);
    }

    @Override
    public List<UserProgressDto> getUserProgress(UserProgressDto userProgressDto) {
        return Converter.listToList(
                getFullUserProgress(userProgressDto),
                UserProgressDto::fromUserProgress
        );
    }

    @Override
    public List<UserProgress> getFullUserProgress(UserProgressDto userProgressDto) {
        if(userProgressDto.getLabWorkId() == null){
            return userProgressRepository.findByUserId(
                    userProgressDto.getUserId()
            );
        } else if(userProgressDto.getTaskId() == null){
            return userProgressRepository.findByUserIdAndLabWorkId(
                    userProgressDto.getUserId(),
                    userProgressDto.getLabWorkId()
            );
        } else if(userProgressDto.getQuestionId() == null){
            return userProgressRepository.findByUserIdAndLabWorkIdAndTaskId(
                    userProgressDto.getUserId(),
                    userProgressDto.getLabWorkId(),
                    userProgressDto.getTaskId()
            );
        } else{
            return userProgressRepository.findByUserIdAndLabWorkIdAndTaskIdAndQuestionId(
                    userProgressDto.getUserId(),
                    userProgressDto.getLabWorkId(),
                    userProgressDto.getTaskId(),
                    userProgressDto.getQuestionId()
            );
        }
    }

    @Override
    public void saveUserProgress(UserProgress userProgress) {
        List<UserProgress> savedUserProgresses = getFullUserProgress(UserProgressDto.fromUserProgress(userProgress));
        if (savedUserProgresses.size() > 1){
            throw new RuntimeException("Почему-то в базе лежит два одинаковых прогресса пользователя");
        }
        if (savedUserProgresses.size() == 1){
            userProgressRepository.save(savedUserProgresses.get(0));
        } else{
            userProgressRepository.save(userProgress);
        }
    }
}
