package kurlyk.controllers;

import kurlyk.services.task.TaskService;
import kurlyk.transfer.QuestionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping("/task")
    public ResponseEntity<Object> postTask(@RequestBody QuestionDto questionDto) {
        taskService.post(questionDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/task")
    public QuestionDto getTask(@RequestBody String taskName) {
        return taskService.getTaskByName(taskName);
    }

    @GetMapping("/lab/{lab-number}")
    public List<QuestionDto> getLab(@PathVariable("lab-number") Integer labNumber) {
        return taskService.getLab(labNumber);
    }
}
