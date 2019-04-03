package kurlyk.controllers;


import kurlyk.models.Task;
import kurlyk.services.task.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

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
}
