package kurlyk.services.competenceService;

import kurlyk.model.Competence;
import kurlyk.model.LabWorkCompetence;
import kurlyk.repositories.CompetenceRepository;
import kurlyk.repositories.LabWorkCompetenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompetenceServiceImpl implements CompetenceService{

    @Autowired
    CompetenceRepository competenceRepository;

    @Autowired
    LabWorkCompetenceRepository labWorkCompetenceRepository;


    @Override
    public Optional<Competence> getCompetence(Long id) {
        return competenceRepository.findById(id);
    }

    @Override
    public List<Competence> getCompetences() {
        return competenceRepository.findAll();
    }

    @Override
    public Long saveCompetence(Competence competence) {
        return competenceRepository.save(competence).getId();
    }

    @Override
    public void deleteCompetence(Long id) {
        competenceRepository.deleteById(id);
    }

    @Override
    public List<LabWorkCompetence> getLabWorkCompetencesByLabWorkId(Long labWorkId) {
        return labWorkCompetenceRepository.findByByLabWorkId(labWorkId);
    }

    @Override
    public Long saveLabWorkCompetenceCompetence(LabWorkCompetence labWorkCompetence) {
        return labWorkCompetenceRepository.save(labWorkCompetence).getId();
    }

    @Override
    public void deleteLabWorkCompetence(Long id) {
        labWorkCompetenceRepository.deleteById(id);
    }
}
