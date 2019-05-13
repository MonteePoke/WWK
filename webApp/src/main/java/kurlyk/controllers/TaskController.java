package kurlyk.controllers;


import kurlyk.models.Task;
import kurlyk.models.TaskQuestion;
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
    public ResponseEntity<Object> saveTask(@RequestBody Task task) {
        taskService.saveTask(task);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/task/delete/{id}")
    public ResponseEntity<Object> deleteTask(@PathVariable("id") Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/task-question-matching")
    public List<TaskQuestion> getTaskQuestionMatching() {
        return taskService.getTaskQuestionMatching();
    }

    @GetMapping("/task-question-matching/{id}")
    public List<TaskQuestion> getTaskQuestionMatching(@PathVariable("id") Long taskId) {
        return taskService.getTaskQuestionMatching(taskId);
    }

    @PostMapping("/task-question-matching")
    public ResponseEntity<Object> saveTaskQuestionMatching(@RequestBody TaskQuestion taskQuestion) {
        taskService.saveTaskQuestionMatching(taskQuestion);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/task-question-matching/delete/{id}")
    public ResponseEntity<Object> deleteTaskQuestionMatching(@PathVariable("id") Long id) {
        taskService.deleteTaskQuestionMatching(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/task-question-matching/task/{id}")
    public ResponseEntity<Object> deleteTaskQuestionMatchingByTask(@PathVariable("id") Long id) {
        try {
            taskService.deleteTaskQuestionMatchingByTaskId(id);
        } catch (EmptyResultDataAccessException e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/task-question-matching/question/{id}")
    public ResponseEntity<Object> deleteTaskQuestionMatchingByQuestion(@PathVariable("id") Long id) {
        try {
            taskService.deleteTaskQuestionMatchingByQuestionId(id);
        } catch (EmptyResultDataAccessException e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }
}
