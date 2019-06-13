package kurlyk.controllers;


import kurlyk.model.Task;
import kurlyk.services.task.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping("/task/{id}")
    public Task getTask(@PathVariable("id") Long id) {
        return taskService.getTask(id).orElseThrow(RuntimeException::new);
    }

    @GetMapping("/tasks/{lab-work-id}")
    public List<Task> getTasks(@PathVariable("lab-work-id") Long labWorkId) {
        return taskService.getTasks(labWorkId);
    }

    @GetMapping("/tasks")
    public List<Task> getTasks() {
        return taskService.getTasks();
    }

    @PostMapping("/task")
    public Long saveTask(@RequestBody Task task) {
        return taskService.saveTask(task);
    }

    @DeleteMapping("/task/{id}")
    public ResponseEntity<Object> deleteTask(@PathVariable("id") Long id) {
        try {
            taskService.deleteTask(id);
        } catch (EmptyResultDataAccessException e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }
}
