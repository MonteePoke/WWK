package kurlyk.view.lab;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import kurlyk.view.fxCommon.Controller;
import org.springframework.stereotype.Component;

@Component
public class LabController extends Controller {

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


    protected void initialize(){
        serverConfigButton.setOnAction(event -> {

        });

        localConfigButton.setOnKeyPressed(event -> {

        });

        localRunButton.setOnKeyPressed(event -> {

        });

        serverRunButton.setOnKeyPressed(event -> {

        });
    }
}
