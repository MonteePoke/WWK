package kurlyk.repositories;


import kurlyk.models.LabWork;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LabWorkRepository extends JpaRepository<LabWork, Long> {

    Optional<LabWork> findOneByName(String name);
}
