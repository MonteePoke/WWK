package kurlyk.controllers;


import kurlyk.models.Question;
import kurlyk.services.question.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/question/{id}")
    public Question getQuestion(@PathVariable("id") Long id) {
        return questionService.getQuestion(id).orElseThrow(RuntimeException::new);
    }

    @GetMapping("/questions/{task-id}")
    public List<Question> getQuestions(@PathVariable("task-id") Long taskId) {
        return questionService.getQuestions(taskId);
    }

    @PostMapping("/question")
    public ResponseEntity<Object> saveQuestion(@RequestBody Question question) {
        questionService.saveQuestion(question);
        return ResponseEntity.ok().build();
    }
}
