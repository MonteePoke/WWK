package kurlyk.view.signInWindow;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import kurlyk.view.common.Controller;
import kurlyk.view.common.stage.StagePool;
import kurlyk.view.common.stage.Stages;
import kurlyk.view.drawComputerSystemWindow.DrawComputerSystemStage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

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
            stagePool.pushStageAndShow(Stages.DRAW_COMPUTER_SYSTEM, new DrawComputerSystemStage());
            stagePool.closeStage(Stages.SIGN_IN);
        });

        serverRunButton.setOnAction(event -> {

        });
    }
}