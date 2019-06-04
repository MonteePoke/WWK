package kurlyk.view.signInWindow;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import kurlyk.communication.Communicator;
import kurlyk.communication.UserInfo;
import kurlyk.models.Role;
import kurlyk.transfer.LoginDto;
import kurlyk.view.common.controller.Controller;
import kurlyk.view.common.dto.BaseStageDto;
import kurlyk.view.common.stage.StagePool;
import kurlyk.view.common.stage.Stages;
import kurlyk.view.components.labTreeView.tree.TreeSceneCreator;
import kurlyk.view.startWindow.StartStage;
import kurlyk.view.startWindow.student.StartStudentSceneCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.ConnectException;

@Component
@Scope("prototype")
public class SignInController extends Controller {

    @FXML public TextField loginField;
    @FXML private PasswordField passwordField;
    @FXML private Button logInButton;
    @FXML private Button logInAsGuestButton;
    @FXML private Label feedback;

    private boolean loginFieldEmpty = false;
    private boolean passwordFieldEmpty = false;

    @Autowired
    private StagePool stagePool;

    @Autowired
    private UserInfo userInfo;

    @Autowired
    private Communicator communicator;


    public void initialize(){
        logInAsGuestButton.setOnAction(event -> {
//            stagePool.pushStageAndShow(Stages.COMPUTER_SYSTEM, new ComputerSystemDiagramStage());
//            stagePool.pushStageAndShow(Stages.FORMULA, new FormulaStage());
//            stagePool.pushStageAndShow(Stages.TEXT, new TextStage());
//            stagePool.pushStageAndShow(Stages.NUMBER, new NumberStage());
//            MatchingDto matchingDto = new MatchingDto(Arrays.asList("111", "222", "333"), Arrays.asList("111", "222", "333"));
//            stagePool.pushStageAndShow(Stages.MATCHING, new MatchingStage(matchingDto));
//            SelectDto checkDto = new SelectDto(Arrays.asList(new Pair<>("a1", true), new Pair<>("b2", false), new Pair<>("c3", false), new Pair<>("d4", true)));
//            stagePool.pushStageAndShow(Stages.CHECK, new CheckStage(checkDto));
//            SelectDto radioDto = new SelectDto(Arrays.asList(new Pair<>("a1", false), new Pair<>("b2", true), new Pair<>("c3", false), new Pair<>("d4", false)));
//            stagePool.pushStageAndShow(Stages.RADIO, new RadioStage(radioDto));

//            stagePool.deleteStage(Stages.SIGN_IN);
        });

        logInButton.setOnAction(event -> signIn());

        loginField.textProperty().addListener((observable, oldValue, newValue) -> {
            validateButtons();

        });

        passwordField.textProperty().addListener((observable, oldValue, newValue) -> {
            validateButtons();
        });
    }

    private void validateButtons(){
        loginFieldEmpty = loginField.getText().equals("");
        passwordFieldEmpty = passwordField.getText().equals("");

        if(loginFieldEmpty){
            logInButton.setDisable(true);
            logInAsGuestButton.setDisable(true);
        } else if (passwordFieldEmpty){
            logInButton.setDisable(true);
            logInAsGuestButton.setDisable(false);
        } else {
            logInButton.setDisable(false);
            logInAsGuestButton.setDisable(false);
        }
    }

    public void signIn(){
        LoginDto loginDto = LoginDto
                .builder()
                .login(loginField.getText())
                .password(passwordField.getText())
                .build();
        try {
            if(!communicator.login(loginDto)){
                feedback.setVisible(true);
                return;
            } else {
                feedback.setVisible(false);
            }
            stagePool.pushStage(Stages.START, new StartStage());
            if (userInfo.getTokenDto().getUserRole() == Role.ADMIN) {
                stagePool.getStage(Stages.START).setScene(new TreeSceneCreator(BaseStageDto.allInclusive()).getScene());
            } else {
                stagePool.getStage(Stages.START).setScene(new StartStudentSceneCreator().getScene());
            }
            stagePool.showStage(Stages.START);
            stagePool.deleteStage(Stages.SIGN_IN);
        } catch (ConnectException e) {
            System.out.println("Нет соединения с сервером");
        } catch (IOException e){
            System.out.println("Ошибка авторизации");
        }
    }
}