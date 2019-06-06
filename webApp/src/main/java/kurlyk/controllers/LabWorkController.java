package kurlyk.controllers;


import kurlyk.models.LabWork;
import kurlyk.services.labWork.LabWorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LabWorkController {

    @Autowired
    private LabWorkService labWorkService;

    @GetMapping("/lab-work/{id}")
    public LabWork getLabWork(@PathVariable("id") Long id) {
        return labWorkService.getLabWork(id).orElseThrow(RuntimeException::new);
    }

    @GetMapping("/lab-works")
    public List<LabWork> getLabWorks() {
        return labWorkService.getLabWorks();
    }

    @PostMapping("/lab-work")
    public Long saveLabWork(@RequestBody LabWork labWork) {
        return labWorkService.saveLabWork(labWork);
    }

    @DeleteMapping("/lab-work/{id}")
    public ResponseEntity<Object> deleteLabWork(@PathVariable("id") Long id) {
        try {
            labWorkService.deleteLabWork(id);
        } catch (EmptyResultDataAccessException e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }
}
