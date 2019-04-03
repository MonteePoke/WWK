package kurlyk.repositories;


import kurlyk.models.UserProgress;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserProgressRepository extends JpaRepository<UserProgress, Long> {

    List<UserProgress> findByUserId(
            Long userId
    );

    List<UserProgress> findByUserIdAndLabWorkId(
            Long userId,
            Long labWorkId
    );

    List<UserProgress> findByUserIdAndLabWorkIdAndTaskId(
            Long userId,
            Long labWorkId,
            Long taskId
    );

    List<UserProgress> findByUserIdAndLabWorkIdAndTaskIdAndQuestionId(
            Long userId,
            Long labWorkId,
            Long taskId,
            Long questionId
    );
}
