package kurlyk.view.showResultWindow;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import kurlyk.transfer.ExecuteCallbackDto;
import kurlyk.view.common.controller.Controller;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class ShowResultController extends Controller {

    @FXML Label resultLabel;
    @FXML TextField scoreField;
    @FXML TextField maxScoreField;
    @FXML TextField questionNumberField;
    @FXML TextField attemptsNumberField;
    @FXML Button further;


    public void initialize(){ }

    public void setResult(ExecuteCallbackDto executeCallbackDto, Runnable furtherCallback){
        resultLabel.setText(buildHeadline(executeCallbackDto));
        scoreField.setText(executeCallbackDto.getResultDto().getScore().toString());
        maxScoreField.setText(executeCallbackDto.getResultDto().getMaxScore().toString());
        questionNumberField.setText(executeCallbackDto.getResultDto().getQuestionsNumber().toString());
        attemptsNumberField.setText(executeCallbackDto.getResultDto().getAttemptsNumber().toString());

        further.setOnAction(event -> furtherCallback.run());
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