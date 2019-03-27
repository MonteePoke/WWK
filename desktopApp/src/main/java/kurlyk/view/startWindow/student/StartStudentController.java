package kurlyk.view.startWindow.student;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import kurlyk.communication.Communicator;
import kurlyk.transfer.QuestionDto;
import kurlyk.view.common.controller.Controller;
import kurlyk.view.common.stage.StagePool;
import kurlyk.view.common.stage.Stages;
import kurlyk.view.performLabWindow.PerformLabStage;
import kurlyk.view.utils.FxDialogs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
@Scope("prototype")
public class StartStudentController extends Controller {

    @FXML private ComboBox<Integer> labNumber;
    @FXML private Button further;


    @Autowired
    private StagePool stagePool;

    @Autowired
    private Communicator communicator;


    public void initialize(){
        labNumber.getItems().addAll(1, 2, 3, 4, 5, 6, 7);
        further.setOnAction(event -> {
            try{
                List<QuestionDto> questionDtos = communicator.getLab(labNumber.getValue());
                stagePool.pushStageAndShow(Stages.PERFORM_LAB, new PerformLabStage(questionDtos));
                stagePool.closeStage(Stages.START);
            } catch (IOException e) {
                FxDialogs.showError("", "Ошибка отправки данных");
            }
        });
    }
}