package kurlyk.controllers;


import kurlyk.models.LabWork;
import kurlyk.models.LabWorkTask;
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

    @GetMapping("/lab-work/delete/{id}")
    public ResponseEntity<Object> deleteLabWork(@PathVariable("id") Long id) {
        labWorkService.deleteLabWork(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/lab-work-task-matching")
    public List<LabWorkTask> getLabWorkTaskMatching() {
        return labWorkService.getLabWorkTaskMatching();
    }

    @GetMapping("/lab-work-task-matching/{id}")
    public List<LabWorkTask> getLabWorkTaskMatching(@PathVariable("id") Long labWorkId) {
        return labWorkService.getLabWorkTaskMatching(labWorkId);
    }

    @PostMapping("/lab-work-task-matching")
    public Long saveLabWorkTaskMatching(@RequestBody LabWorkTask labWorkTask) {
        return labWorkService.saveLabWorkTaskMatching(labWorkTask);
    }

    @GetMapping("/lab-work-task-matching/delete/{id}")
    public ResponseEntity<Object> deleteLabWorkTaskMatching(@PathVariable("id") Long id) {
        labWorkService.deleteLabWorkTaskMatching(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/lab-work-task-matching/lab-work/{id}")
    public ResponseEntity<Object> deleteLabWorkTaskMatchingByLabWork(@PathVariable("id") Long id) {
        try {
            labWorkService.deleteLabWorkTaskMatchingByLabWorkId(id);
        } catch (EmptyResultDataAccessException e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/lab-work-task-matching/task/{id}")
    public ResponseEntity<Object> deleteLabWorkTaskMatchingByTask(@PathVariable("id") Long id) {
        try {
            labWorkService.deleteLabWorkTaskMatchingByTaskId(id);
        } catch (EmptyResultDataAccessException e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }
}
