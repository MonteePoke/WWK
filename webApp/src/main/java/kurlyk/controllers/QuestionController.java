package kurlyk.controllers;


import kurlyk.models.Question;
import kurlyk.services.question.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
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

    @GetMapping("/questions")
    public List<Question> getQuestions(
            @RequestParam("pageNumber") Integer pageNumber,
            @RequestParam("contentSize") Integer contentSize
    ) {
        return questionService.getQuestions(pageNumber, contentSize);
    }

    @GetMapping("/question-headers/{task-id}")
    public List<Question> getQuestionHeaders(@PathVariable("task-id") Long taskId) {
        return questionService.getQuestionHeaders(taskId);
    }

    @GetMapping("/questions-headers")
    public List<Question> getQuestionHeaders() {
        return questionService.getQuestionHeaders();
    }

    @PostMapping("/question")
    public Long saveQuestion(@RequestBody Question question) {
        return questionService.saveQuestion(question);
    }

    @DeleteMapping("/question/{id}")
    public ResponseEntity<Object> deleteQuestion(@PathVariable("id") Long id) {
        try {
            questionService.deleteQuestion(id);
        } catch (EmptyResultDataAccessException e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }
}
