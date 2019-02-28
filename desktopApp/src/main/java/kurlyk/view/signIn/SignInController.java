package kurlyk.view.signIn;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import kurlyk.view.fxCommon.Controller;
import kurlyk.view.lab.LabStage;
import lombok.Getter;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
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


    public void initialize(){
        serverConfigButton.setOnAction(event -> {

        });

        localConfigButton.setOnAction(event -> {

        });

        localRunButton.setOnAction(event -> {
            LabStage labStage = new LabStage();
            labStage.show();
        });

        serverRunButton.setOnAction(event -> {

        });
    }
}
