package kurlyk.controllers;


import kurlyk.models.Subject;
import kurlyk.services.subject.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SubjectController {

    @Autowired
    private SubjectService subjectService;

    @GetMapping("/subject/{id}")
    public Subject getSubject(@PathVariable("id") Long id) {
        return subjectService.getSubject(id).orElse(Subject.builder().id(-1L).build());
    }

    @GetMapping("/subjects")
    public List<Subject> getSubjects() {
        return subjectService.getSubjects();
    }

    @PostMapping("/subject")
    public Long saveSubject(@RequestBody Subject subject) {
        return subjectService.saveSubject(subject);
    }

    @DeleteMapping("/subject/{id}")
    public ResponseEntity<Object> deleteSubject(@PathVariable("id") Long id) {
        try {
            subjectService.deleteSubject(id);
        } catch (EmptyResultDataAccessException e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }
}
