package kurlyk.repositories;


import kurlyk.models.UsverProgressLabWork;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsverProgressRepository extends JpaRepository<UsverProgressLabWork, Long> {

    List<UsverProgressLabWork> findByUsverId(Long userId);

    List<UsverProgressLabWork> findByUsverIdAndLabWorkId(Long userId, Long labWorkId);
}
