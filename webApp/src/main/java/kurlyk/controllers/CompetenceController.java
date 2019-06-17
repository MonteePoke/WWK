package kurlyk.controllers;

import kurlyk.model.Competence;
import kurlyk.model.LabWorkCompetence;
import kurlyk.repositories.*;
import kurlyk.services.competenceService.CompetenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CompetenceController {

    @Autowired
    private CompetenceService competenceService;

    @GetMapping("/competence/{id}")
    public Competence getCompetence(@PathVariable("id") Long id) {
        return competenceService.getCompetence(id).orElseThrow(RuntimeException::new);
    }

    @GetMapping("/competences")
    public List<Competence> getCompetences() {
        return competenceService.getCompetences();
    }

    @PostMapping("/competence")
    public Long saveSubject(@RequestBody Competence competence) {
        return competenceService.saveCompetence(competence);
    }

    @DeleteMapping("/competence/{id}")
    public ResponseEntity<Object> deleteCompetence(@PathVariable("id") Long id) {
        try {
            competenceService.deleteCompetence(id);
        } catch (EmptyResultDataAccessException e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }


    @GetMapping("/lab-work-competences/{id}")
    public List<LabWorkCompetence> getLabWorkCompetences(@PathVariable("id") Long labWorkId) {
        return competenceService.getLabWorkCompetencesByLabWorkId(labWorkId);
    }

    @PostMapping("/lab-work-competence")
    public Long saveSubject(@RequestBody LabWorkCompetence labWorkCompetence) {
        return competenceService.saveLabWorkCompetenceCompetence(labWorkCompetence);
    }

    @DeleteMapping("/lab-work-competence/{id}")
    public ResponseEntity<Object> deleteLabWorkCompetence(@PathVariable("id") Long id) {
        try {
            competenceService.deleteLabWorkCompetence(id);
        } catch (EmptyResultDataAccessException e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }

    @Autowired
    private LabWorkRepository labWorkRepository;
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private UsverProgressLabWorkRepository usverProgressLabWorkRepository;
    @Autowired
    private UsverProgressTaskRepository usverProgressTaskRepository;
    @Autowired
    private UsverProgressQuestionRepository usverProgressQuestionRepository;


    //удаление лабораторной
    @GetMapping("/x")
    @Transactional
    public void getX() {
        usverProgressLabWorkRepository.deleteByLabWorkId(1L);
        labWorkRepository.deleteById(1L);
    }

    //удаление задания
    @GetMapping("/y")
    @Transactional
    public void getY() {
        usverProgressLabWorkRepository.deleteByLabWorkId(1L);
        labWorkRepository.deleteById(1L);
    }

    //удаление вопроса
    @GetMapping("/z")
    @Transactional
    public void getZ() {
        usverProgressQuestionRepository.deleteByQuestionId(1L);
        questionRepository.deleteById(1L);
    }
}
