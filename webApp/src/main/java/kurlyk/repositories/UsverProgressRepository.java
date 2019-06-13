package kurlyk.repositories;


import kurlyk.model.UsverProgressLabWork;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UsverProgressRepository extends JpaRepository<UsverProgressLabWork, Long> {

    List<UsverProgressLabWork> findByUsverId(Long usverId);

    Optional<UsverProgressLabWork> findOneByUsverIdAndLabWorkId(Long usverId, Long labWorkId);
}
