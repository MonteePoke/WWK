package kurlyk.repositories;


import kurlyk.model.LabWorkCompetence;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LabWorkCompetenceRepository extends JpaRepository<LabWorkCompetence, Long> {
    List<LabWorkCompetence> findByByLabWorkId(Long labWorkId);
}
