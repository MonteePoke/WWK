package kurlyk.controllers;


import kurlyk.services.testAnswerService.TestAnswerService;
import kurlyk.transfer.ResultAnswerDto;
import kurlyk.transfer.answer.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class TestAnswerController {

    @Autowired
    private TestAnswerService testAnswerService;

    @PostMapping("/test-computer-system")
    public ResultAnswerDto testComputerSystemAnswer(@RequestBody ComputerSystemAnswerDto dto){
        return testAnswerService.testComputerSystemAnswer(dto);
    }

    @PostMapping("/test-formula")
    public ResultAnswerDto testFormulaAnswer(@RequestBody FormulaAnswerDto dto){
        return testAnswerService.testFormulaAnswer(dto);
    }

    @PostMapping("/test-text")
    public ResultAnswerDto testTextAnswer(@RequestBody TextAnswerDto dto){
        return testAnswerService.testTextAnswer(dto);
    }

    @PostMapping("/test-number")
    public ResultAnswerDto testNumberAnswer(@RequestBody NumberAnswerDto dto){
        return testAnswerService.testNumberAnswer(dto);
    }

    @PostMapping("/test-matching")
    public ResultAnswerDto testMatchingAnswer(@RequestBody MatchingAnswerDto dto){
        return testAnswerService.testMatchingAnswer(dto);
    }

    @PostMapping("/test-check")
    public ResultAnswerDto testCheckAnswer(@RequestBody SelectAnswerDto dto){
        return testAnswerService.testCheckAnswer(dto);
    }

    @PostMapping("/test-radio")
    public ResultAnswerDto testRadioAnswer(@RequestBody SelectAnswerDto dto){
        return testAnswerService.testRadioAnswer(dto);
    }

    @PostMapping("/test-sorting")
    public ResultAnswerDto testSortingAnswer(@RequestBody SortingAnswerDto dto){
        return testAnswerService.testSortingAnswer(dto);
    }
}