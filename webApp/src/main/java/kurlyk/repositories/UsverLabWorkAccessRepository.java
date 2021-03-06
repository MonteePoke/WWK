package kurlyk.repositories;


import kurlyk.model.UsverLabWorkAccess;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsverLabWorkAccessRepository extends JpaRepository<UsverLabWorkAccess, Long> {

    Optional<UsverLabWorkAccess> findOneByLabWorkIdAndUsverId(Long labWorkId, Long usverId);

}
