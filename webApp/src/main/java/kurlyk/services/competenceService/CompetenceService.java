package kurlyk.services.competenceService;

import kurlyk.model.Competence;
import kurlyk.model.LabWorkCompetence;

import java.util.List;
import java.util.Optional;

public interface CompetenceService {
    Optional<Competence> getCompetence(Long id);
    List<Competence> getCompetences();
    Long saveCompetence(Competence competence);
    void deleteCompetence(Long id);

    List<LabWorkCompetence> getLabWorkCompetencesByLabWorkId(Long labWorkId);
    Long saveLabWorkCompetenceCompetence(LabWorkCompetence labWorkCompetence);
    void deleteLabWorkCompetence(Long id);
}
