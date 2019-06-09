package kurlyk.controllers;


import kurlyk.models.LabWork;
import kurlyk.models.Question;
import kurlyk.services.labWork.LabWorkService;
import kurlyk.services.question.QuestionService;
import kurlyk.services.task.TaskService;
import kurlyk.transfer.QuestionIdsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class LabWorkController {

    @Autowired
    private LabWorkService labWorkService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private QuestionService questionService;

    @GetMapping("/lab-work/{id}")
    public LabWork getLabWork(@PathVariable("id") Long id) {
        return labWorkService.getLabWork(id).orElseThrow(RuntimeException::new);
    }

    @GetMapping("/questions-for-execute")
    public QuestionIdsDto getQuestionsForExecute(
            @RequestParam("labWorkId") Long labWorkId,
            @RequestParam("variant") Integer variant
    ) {
        List<Question> questions = taskService.getTaskIds(labWorkId)
                .stream()
                .flatMap(taskId -> questionService.getQuestionIdAndNumbers(taskId).stream())
                .collect(Collectors.toList());
        return QuestionIdsDto.from(questions);
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
