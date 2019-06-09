package kurlyk.view.showResultWindow;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import kurlyk.communication.Communicator;
import kurlyk.communication.UsverInfo;
import kurlyk.transfer.ResultDto;
import kurlyk.transfer.UsverLabWorkDto;
import kurlyk.view.common.controller.Controller;
import kurlyk.view.common.stage.StagePool;
import kurlyk.view.common.stage.Stages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.stream.Collectors;

@Component
@Scope("prototype")
public class ShowResultController extends Controller {

    @FXML TextField correctAnswerNumber;
    @FXML TextField wrongAnswerNumber;
    @FXML TextField score;
    @FXML TextField errorsNumber;
    @FXML Button further;

    private UsverLabWorkDto usverLabWorkDto;
    private boolean isTest;


    // Если false, что результаты ещё не показаны и надо показать.
    // Если true, то результаты показаны и можно переходить к следующему этапу
    private boolean resultAreShown;
    private Runnable startLabCallback;
    private Runnable showResultCallback;

    @Autowired
    private StagePool stagePool;

    @Autowired
    private UsverInfo usverInfo;

    @Autowired
    private Communicator communicator;


    public void initialize(){
        resultAreShown = false;
        further.setOnAction(event -> {
            if (!resultAreShown) {
                showResultCallback.run();
                further.setText("Далее");
                ResultDto resultDto = getResult();
                correctAnswerNumber.setText(resultDto.getCorrectAnswerNumber().toString());
                wrongAnswerNumber.setText(resultDto.getWrongAnswerNumber().toString());
                score.setText(resultDto.getScore().toString());
                errorsNumber.setText(resultDto.getScore().toString());
                resultAreShown = true;
            } else {
                startLabCallback.run();
                if (isTest) {
                    stagePool.deleteStage(Stages.PERFORM_TEST);
                    stagePool.showStage(Stages.PERFORM_LAB);
                } else {
                    stagePool.deleteStage(Stages.PERFORM_LAB);
                    stagePool.showStage(Stages.START);
                }
            }
        });
    }

    public void setResultOwner(Long labWorkId, Long userId, boolean isTest, Runnable startLabCallback, Runnable showResultCallback){
        usverLabWorkDto = UsverLabWorkDto.builder()
                .labWorkId(labWorkId)
                .usverId(userId)
                .build();
        this.isTest = isTest;
        this.startLabCallback = startLabCallback;
        this.showResultCallback = showResultCallback;
    }

    private ResultDto getResult(){
        try {
            return new ResultDto(
                    communicator.getFullUserProgress(usverLabWorkDto)
                            .stream()
                            .filter(userProgress -> userProgress.getTask().getIsTest().equals(isTest))
                            .collect(Collectors.toList())
            );
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }
}