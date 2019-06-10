package kurlyk.repositories;


import kurlyk.models.UsverProgressLabWork;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UsverProgressRepository extends JpaRepository<UsverProgressLabWork, Long> {

    List<UsverProgressLabWork> findByUsverId(Long userId);

    Optional<UsverProgressLabWork> findOneByUsverIdAndLabWorkId(Long userId, Long labWorkId);
}
