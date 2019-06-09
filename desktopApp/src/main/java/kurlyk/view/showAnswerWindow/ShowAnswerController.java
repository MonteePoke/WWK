package kurlyk.view.showAnswerWindow;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;
import kurlyk.communication.Communicator;
import kurlyk.communication.UsverInfo;
import kurlyk.view.common.ViewProperties;
import kurlyk.view.common.controller.Controller;
import kurlyk.view.common.stage.StagePool;
import kurlyk.view.components.FxDialogs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class ShowAnswerController extends Controller {

    @FXML private PasswordField masterKodField;
    @FXML private Button showAnswer;
    private Long questionId;
    private Stage stage;

    @Autowired
    private StagePool stagePool;

    @Autowired
    private UsverInfo usverInfo;

    @Autowired
    private Communicator communicator;


    public void initialize(){
        showAnswer.setOnAction(event -> {
            if (masterKodField.getText().equals(getMasterKod())) {
//                answerField.setText(question.getDescription());
            } else {
                FxDialogs.showError("Ошибка",  "Неверный пароль");
            }
        });
    }

    public void setQuestion(Long questionId) {
        this.questionId = questionId;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    private String getMasterKod(){
        return ViewProperties.getInstance().getProperty("masterKod");
    }
}