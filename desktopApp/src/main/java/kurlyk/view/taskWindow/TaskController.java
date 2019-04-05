package kurlyk.view.taskWindow;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.web.HTMLEditor;
import javafx.util.Pair;
import kurlyk.transfer.tasks.SelectDto;
import kurlyk.view.common.controller.Controller;
import kurlyk.view.common.stage.StagePool;
import kurlyk.view.common.stage.Stages;
import kurlyk.view.task.checkWindow.CheckStage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Scope("prototype")
public class TaskController extends Controller {

    @FXML public HTMLEditor htmlEditor;
    @FXML private TextField passwordInput;
    @FXML private Button guestRunButton;
    @FXML private Button userRunButton;

    @Autowired
    private StagePool stagePool;


    public void initialize(){

        guestRunButton.setOnAction(event -> {
//            stagePool.pushStageAndShow(Stages.COMPUTER_SYSTEM, new ComputerSystemDiagramStage());
//            stagePool.pushStageAndShow(Stages.FORMULA, new FormulaStage());
//            stagePool.pushStageAndShow(Stages.TEXT, new TextStage());
//            stagePool.pushStageAndShow(Stages.NUMBER, new NumberStage());
//            MatchingDto matchingDto = new MatchingDto(Arrays.asList("111", "222", "333"), Arrays.asList("111", "222", "333"));
//            stagePool.pushStageAndShow(Stages.MATCHING, new MatchingStage(matchingDto));
            SelectDto checkDto = new SelectDto(Arrays.asList(new Pair<>("a1", true), new Pair<>("b2", false), new Pair<>("c3", false), new Pair<>("d4", true)));
            stagePool.pushStageAndShow(Stages.CHECK, new CheckStage(checkDto));
//            SelectDto radioDto = new SelectDto(Arrays.asList(new Pair<>("a1", false), new Pair<>("b2", true), new Pair<>("c3", false), new Pair<>("d4", false)));
//            stagePool.pushStageAndShow(Stages.RADIO, new RadioStage(radioDto));
            stagePool.deleteStage(Stages.SIGN_IN);
        });

        userRunButton.setOnAction(event -> {

        });
    }
}