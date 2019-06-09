package kurlyk.services.testAnswerService;

import com.google.gson.Gson;
import kurlyk.common.StemmerPorterRU;
import kurlyk.models.Question;
import kurlyk.services.labWork.LabWorkService;
import kurlyk.services.question.QuestionService;
import kurlyk.services.task.TaskService;
import kurlyk.services.usver.UsverService;
import kurlyk.transfer.ResultAnswerDto;
import kurlyk.transfer.answer.*;
import kurlyk.transfer.tasks.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@Service
public class testAnswerServiceImpl implements TestAnswerService {

    @Autowired
    private LabWorkService labWorkService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private QuestionService questionService;

    @Autowired
    private UsverService usverService;

    public ResultAnswerDto testComputerSystemAnswer(@RequestBody ComputerSystemAnswerDto dto){
        Optional<Question> optionalQuestion = questionService.getQuestion(dto.getQuestionId());
        ResultAnswerDto resultAnswerDto = new ResultAnswerDto();
        if(optionalQuestion.isPresent()){
            ComputerSystemDto standart = new Gson().fromJson(optionalQuestion.get().getAnswer(), ComputerSystemDto.class);
            resultAnswerDto.setScore(
                    percentToScore(dto, testComputerSystemDto(standart, dto.getEntity()))
            );
        } else {
            resultAnswerDto.setQuestionNotFound(true);
        }
        return resultAnswerDto;
    }

    public ResultAnswerDto testFormulaAnswer(FormulaAnswerDto dto){
        Optional<Question> optionalQuestion = questionService.getQuestion(dto.getQuestionId());
        ResultAnswerDto resultAnswerDto = new ResultAnswerDto();
        if(optionalQuestion.isPresent()){
            FormulaDto standart = new Gson().fromJson(optionalQuestion.get().getAnswer(), FormulaDto.class);
            resultAnswerDto.setScore(
                    percentToScore(dto, testFormulaDto(standart, dto.getEntity()))
            );
        } else {
            resultAnswerDto.setQuestionNotFound(true);
        }
        return resultAnswerDto;
    }

    public ResultAnswerDto testTextAnswer(TextAnswerDto dto){
        Optional<Question> optionalQuestion = questionService.getQuestion(dto.getQuestionId());
        ResultAnswerDto resultAnswerDto = new ResultAnswerDto();
        if(optionalQuestion.isPresent()){
            TextDto standart = new Gson().fromJson(optionalQuestion.get().getAnswer(), TextDto.class);
            resultAnswerDto.setScore(
                    percentToScore(dto, testTextDto(standart, dto.getEntity()))
            );
        } else {
            resultAnswerDto.setQuestionNotFound(true);
        }
        return resultAnswerDto;
    }

    public ResultAnswerDto testNumberAnswer(NumberAnswerDto dto){
        Optional<Question> optionalQuestion = questionService.getQuestion(dto.getQuestionId());
        ResultAnswerDto resultAnswerDto = new ResultAnswerDto();
        if(optionalQuestion.isPresent()){
            NumberDto standart = new Gson().fromJson(optionalQuestion.get().getAnswer(), NumberDto.class);
            resultAnswerDto.setScore(
                    percentToScore(dto, testNumberDto(standart, dto.getEntity()))
            );
        } else {
            resultAnswerDto.setQuestionNotFound(true);
        }
        return resultAnswerDto;
    }

    public ResultAnswerDto testMatchingAnswer(MatchingAnswerDto dto){
        Optional<Question> optionalQuestion = questionService.getQuestion(dto.getQuestionId());
        ResultAnswerDto resultAnswerDto = new ResultAnswerDto();
        if(optionalQuestion.isPresent()){
            MatchingDto standart = new Gson().fromJson(optionalQuestion.get().getAnswer(), MatchingDto.class);
            resultAnswerDto.setScore(
                    percentToScore(dto, testMatchingDto(standart, dto.getEntity()))
            );
        } else {
            resultAnswerDto.setQuestionNotFound(true);
        }
        return resultAnswerDto;
    }

    public ResultAnswerDto testCheckAnswer(SelectAnswerDto dto){
        Optional<Question> optionalQuestion = questionService.getQuestion(dto.getQuestionId());
        ResultAnswerDto resultAnswerDto = new ResultAnswerDto();
        if(optionalQuestion.isPresent()){
            SelectDto standart = new Gson().fromJson(optionalQuestion.get().getAnswer(), SelectDto.class);
            resultAnswerDto.setScore(
                    percentToScore(dto, testCheckDto(standart, dto.getEntity()))
            );
        } else {
            resultAnswerDto.setQuestionNotFound(true);
        }
        return resultAnswerDto;
    }

    public ResultAnswerDto testRadioAnswer(SelectAnswerDto dto){
        Optional<Question> optionalQuestion = questionService.getQuestion(dto.getQuestionId());
        ResultAnswerDto resultAnswerDto = new ResultAnswerDto();
        if(optionalQuestion.isPresent()){
            SelectDto standart = new Gson().fromJson(optionalQuestion.get().getAnswer(), SelectDto.class);
            resultAnswerDto.setScore(
                    percentToScore(dto, testRadioDto(standart, dto.getEntity()))
            );
        } else {
            resultAnswerDto.setQuestionNotFound(true);
        }
        return resultAnswerDto;
    }

    public ResultAnswerDto testSortingAnswer(SortingAnswerDto dto){
        Optional<Question> optionalQuestion = questionService.getQuestion(dto.getQuestionId());
        ResultAnswerDto resultAnswerDto = new ResultAnswerDto();
        if(optionalQuestion.isPresent()){
            SortingDto standart = new Gson().fromJson(optionalQuestion.get().getAnswer(), SortingDto.class);
            resultAnswerDto.setScore(
                    percentToScore(dto, testSortingDto(standart, dto.getEntity()))
            );
        } else {
            resultAnswerDto.setQuestionNotFound(true);
        }
        return resultAnswerDto;
    }

    // percent - [0.0 .. 1.0]
    private Long percentToScore(BaseAnswerDto dto, double percent){
        return Math.round(percent);
    }

    // return - [0.0 .. 1.0]
    private double testComputerSystemDto(ComputerSystemDto standart, ComputerSystemDto answer){
        boolean isIsomorfic =  standart.getGraphSystem().isomorfic(answer.getGraphSystem());
        return isIsomorfic ? 1 : 0;
    }

    private double testFormulaDto(FormulaDto standart, FormulaDto answer){
        boolean isEquals =  standart.equals(answer);
        return isEquals ? 1 : 0;
    }

    private double testTextDto(TextDto standart, TextDto answer){
        boolean isAnswerFound = false;
        for (String possibleAnswer : standart.getText().split(TextDto.ANSWER_SEPARATOR)) {
            if(StemmerPorterRU.stemSentence(answer.getText()).equals(StemmerPorterRU.stemSentence(possibleAnswer))){
                isAnswerFound = true;
                break;
            }
        }
        return isAnswerFound ? 1 : 0;
    }

    private double testNumberDto(NumberDto standart, NumberDto answer){
        if(answer.getNumber() == null){
            return 0;
        }
        double standartNumber = standart.getNumber();
        double answerNumber = answer.getNumber();
        int n = standart.getAccuracy() == null ? 0 : standart.getAccuracy();

        if (n <= 0){
            return (standartNumber == answerNumber) ? 1 : 0;
        } else {
            for (int i = 0; i < n; i++){
                standartNumber *= 10;
                answerNumber *= 10;
            }
            long standartResult = Math.round(standartNumber);
            long answerResult = Math.round(answerNumber);

            if((standartResult == answerResult) || (standartResult + 1 == answerResult) || (standartResult - 1 == answerResult)){
                return 1;
            } else {
                return 0;
            }
        }
    }

    private double testMatchingDto(MatchingDto standart, MatchingDto answer){
        boolean isEquals =  standart.equals(answer);
        return isEquals ? 1 : 0;
    }

    private double testCheckDto(SelectDto standart, SelectDto answer){
        boolean isEquals =  standart.equals(answer);
        return isEquals ? 1 : 0;
    }

    private double testRadioDto(SelectDto standart, SelectDto answer){
        boolean isEquals =  standart.equals(answer);
        return isEquals ? 1 : 0;
    }

    private double testSortingDto(SortingDto standart, SortingDto answer){
        boolean isEquals =  standart.equals(answer);
        return isEquals ? 1 : 0;
    }
}
