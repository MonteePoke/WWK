package kurlyk.view.showAnswerWindow;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import kurlyk.communication.Communicator;
import kurlyk.communication.UserInfo;
import kurlyk.models.Question;
import kurlyk.view.common.ViewProperties;
import kurlyk.view.common.controller.Controller;
import kurlyk.view.common.stage.StagePool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class ShowAnswerController extends Controller {

    @FXML private PasswordField masterKodField;
    @FXML private TextField answerField;
    @FXML private Button showAnswer;
    private Question question;

    @Autowired
    private StagePool stagePool;

    @Autowired
    private UserInfo userInfo;

    @Autowired
    private Communicator communicator;


    public void initialize(){
        showAnswer.setOnAction(event -> {
            if (masterKodField.getText().equals(getMasterKod())) {
                answerField.setText(question.getDescription());
            } else {
                answerField.setText("");
            }
        });
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    private String getMasterKod(){
        return ViewProperties.getInstance().getProperty("masterKod");
    }
}