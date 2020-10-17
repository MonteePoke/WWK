package kurlyk.view.showResultWindow;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import kurlyk.common.Duet;
import kurlyk.communication.Communicator;
import kurlyk.communication.ExecuteMaster;
import kurlyk.model.UsverProgressLabWork;
import kurlyk.model.UsverProgressQuestion;
import kurlyk.transfer.ExecuteCallbackDto;
import kurlyk.transfer.QuestionIdsDto;
import kurlyk.view.common.controller.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
@Scope("prototype")
public class ShowResultController extends Controller {

    @FXML Label resultLabel;
    @FXML TextField scoreField;
    @FXML TextField maxScoreField;
    @FXML TextField questionNumberField;
    @FXML TextField attemptsNumberField;
    @FXML Button further;

    @Autowired
    private ExecuteMaster executeMaster;

    @Autowired
    private Communicator communicator;

    public void initialize(){ }

    public void setResult(ExecuteCallbackDto executeCallbackDto, Runnable furtherCallback){
        Long maxScore = 0L;
        Long score = 0L;
        Long attemps = 0L;
        List<Duet<Long,Long>> duets = null;
        try {
            QuestionIdsDto questionIdsDto = communicator.getQuestionsForExecute(executeMaster.getLabWorkId(), executeMaster.getVariant());
            List<UsverProgressQuestion> usverProgressQuestions = communicator.getUsverProgressQuestions(executeMaster.getLabWorkId());
            switch (executeCallbackDto.getResultDto().getType()){
                case TEST:
                    duets = questionIdsDto.getTestQuestionIds();
                    break;
                case LAB_WORK:
                    duets = questionIdsDto.getWorkQuestionIds();
                    break;
            }
            for (Duet<Long, Long> duet : duets) {
                for (UsverProgressQuestion usverProgressQuestion : usverProgressQuestions) {
                    if (duet.getB() == usverProgressQuestion.getQuestion().getId()) {
                        score += usverProgressQuestion.getScore();
                        maxScore += usverProgressQuestion.getQuestion().getScore();
                        attemps += usverProgressQuestion.getAttemptsNumber();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        resultLabel.setText(buildHeadline(executeCallbackDto));
        scoreField.setText(score+"");
        maxScoreField.setText(maxScore+"");
        assert duets != null;
        questionNumberField.setText(duets.size()+"");
        attemptsNumberField.setText(attemps+"");

        further.setOnAction(event ->
                furtherCallback.run()
        );
    }

    private String buildHeadline(ExecuteCallbackDto executeCallbackDto){
        String headline = "Результат ";
        switch (executeCallbackDto.getResultDto().getType()){
            case TEST:
                headline += "теста: ";
                break;
            case LAB_WORK:
                headline += "лабораторной: ";
                break;
        }
        headline += (executeCallbackDto.getIsExecuted() ? "Выполненно" : "Не выполненно");
        return headline;
    }
}