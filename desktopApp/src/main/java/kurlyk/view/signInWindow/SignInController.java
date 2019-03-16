package kurlyk.view.signInWindow;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import kurlyk.transfer.RadioDto;
import kurlyk.view.common.controller.Controller;
import kurlyk.view.common.stage.StagePool;
import kurlyk.view.common.stage.Stages;
import kurlyk.view.radioWindow.RadioStage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Scope("prototype")
public class SignInController extends Controller {

    @FXML private Label nameLabel;
    @FXML public TextField nameInput;
    @FXML private Label groupLabel;
    @FXML private TextField groupInput;
    @FXML private Label configLabel;
    @FXML private Button localConfigButton;
    @FXML private Button serverConfigButton;
    @FXML private Label runLabel;
    @FXML private Button localRunButton;
    @FXML private Button serverRunButton;

    @Autowired
    private StagePool stagePool;


    public void initialize(){
        serverConfigButton.setOnAction(event -> {

        });

        localConfigButton.setOnAction(event -> {

        });

        localRunButton.setOnAction(event -> {
//            stagePool.pushStageAndShow(Stages.COMPUTER_SYSTEM, new ComputerSystemDiagramStage());
//            stagePool.pushStageAndShow(Stages.FORMULA, new FormulaStage());
//            stagePool.pushStageAndShow(Stages.TEXT, new TextStage());
//            stagePool.pushStageAndShow(Stages.NUMBER, new NumberStage());
//            MatchingDto matchingDto = new MatchingDto(Arrays.asList("111", "222", "333"), Arrays.asList("111", "222", "333"));
//            stagePool.pushStageAndShow(Stages.MATCHING, new MatchingStage(matchingDto));
//            CheckDto checkDto = new CheckDto(Arrays.asList("a1", "b2", "c3", "d4"), Arrays.asList(true, false, false, true));
//            stagePool.pushStageAndShow(Stages.CHECK, new CheckStage(checkDto));
            RadioDto radioDto = new RadioDto(Arrays.asList("a1", "b2", "c3", "d4"), 2);
            stagePool.pushStageAndShow(Stages.RADIO, new RadioStage(radioDto));
            stagePool.closeStage(Stages.SIGN_IN);
        });

        serverRunButton.setOnAction(event -> {

        });
    }
}