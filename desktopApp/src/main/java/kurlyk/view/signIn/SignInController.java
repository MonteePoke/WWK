package kurlyk.view.signIn;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import lombok.Getter;

public class SignInController {

    @FXML
    @Getter
    private Label nameLabel;
    @FXML
    @Getter
    public TextField nameInput;

    @FXML
    @Getter
    private Label groupLabel;
    @FXML
    @Getter
    private TextField groupInput;

    @FXML
    @Getter
    private Label configLabel;
    @FXML
    @Getter
    private Button localConfigButton;
    @FXML
    @Getter
    private Button serverConfigButton;

    @FXML
    @Getter
    private Label runLabel;
    @FXML
    @Getter
    private Button localRunButton;
    @FXML
    @Getter
    private Button serverRunButton;
}
