package kurlyk.repositories;


import kurlyk.models.UserLabWorkAccess;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserLabWorkAccessRepository extends JpaRepository<UserLabWorkAccess, Long> {

    Optional<UserLabWorkAccess> findOneByUserIdAndLabWorkId(Long userId, Long labWorkId);
}
