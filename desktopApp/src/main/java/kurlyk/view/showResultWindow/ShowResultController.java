package kurlyk.view.showResultWindow;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import kurlyk.common.Duet;
import kurlyk.common.Quartet;
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
        Quartet<Long, Long, Long, Long> info = null;
        switch (executeCallbackDto.getResultDto().getType()){
            case TEST:
                info = executeMaster.getScoreForTest();
                break;
            case LAB_WORK:
                info = executeMaster.getScoreForWork();
                break;
        }
        assert info != null;
        resultLabel.setText(buildHeadline(executeCallbackDto));
        scoreField.setText(info.getA()+"");
        maxScoreField.setText(info.getB()+"");
        questionNumberField.setText(info.getC()+"");
        attemptsNumberField.setText(info.getD()+"");

        further.setOnAction(event ->
                furtherCallback.run()
        );
    }

    private String buildHeadline(ExecuteCallbackDto executeCallbackDto){
        String headline = "Результат ";
        boolean done = false;
        switch (executeCallbackDto.getResultDto().getType()){
            case TEST:
                headline += "теста: ";
                done = executeMaster.isTestDone();
                break;
            case LAB_WORK:
                headline += "лабораторной: ";
                done = executeMaster.isWorkDone();
                break;
        }
        headline += (done ? "Выполненно" : "Не выполненно");
        return headline;
    }
}