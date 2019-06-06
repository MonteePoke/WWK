package kurlyk.repositories;


import kurlyk.models.UserProgressLabWork;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserProgressRepository extends JpaRepository<UserProgressLabWork, Long> {

    List<UserProgressLabWork> findByUserId(Long userId);

    List<UserProgressLabWork> findByUserIdAndLabWorkId(Long userId, Long labWorkId);
}
