package kurlyk.controllers;

import kurlyk.services.task.TaskService;
import kurlyk.transfer.TaskDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping("/task")
    public ResponseEntity<Object> postTask(@RequestBody TaskDto taskDto) {
        taskService.post(taskDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/task")
    public TaskDto getTask(@RequestBody String taskName) {
        return taskService.getTaskByName(taskName);
    }

    @GetMapping("/lab/{lab-number}")
    public List<TaskDto> getLab(@PathVariable("lab-number") Integer labNumber) {
        return taskService.getLab(labNumber);
    }
}
