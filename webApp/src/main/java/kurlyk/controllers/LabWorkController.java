package kurlyk.controllers;


import kurlyk.models.LabWork;
import kurlyk.models.LabWorkTask;
import kurlyk.services.labWork.LabWorkService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity<Object> saveLabWork(@RequestBody LabWork labWork) {
        labWorkService.saveLabWork(labWork);
        return ResponseEntity.ok().build();
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
    public ResponseEntity<Object> saveLabWorkTaskMatching(@RequestBody LabWorkTask labWorkTask) {
        labWorkService.saveLabWorkTaskMatching(labWorkTask);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/lab-work-task-matching/delete/{id}")
    public ResponseEntity<Object> deleteLabWorkTaskMatching(@PathVariable("id") Long id) {
        labWorkService.deleteLabWorkTaskMatching(id);
        return ResponseEntity.ok().build();
    }
}
