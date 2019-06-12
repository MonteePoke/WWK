package kurlyk.repositories;


import kurlyk.models.UsverLabWorkAccess;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsverLabWorkAccessRepository extends JpaRepository<UsverLabWorkAccess, Long> {

    Optional<UsverLabWorkAccess> findOneByUsverIdAndLabWorkId(Long usverId, Long labWorkId);
}
