package kurlyk.view.signInWindow;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import kurlyk.common.RoleTester;
import kurlyk.communication.Communicator;
import kurlyk.communication.UsverInfo;
import kurlyk.model.enums.RoleEnum;
import kurlyk.transfer.LoginDto;
import kurlyk.view.common.controller.Controller;
import kurlyk.view.common.stage.StagePool;
import kurlyk.view.common.stage.Stages;
import kurlyk.view.startWindow.StartStage;
import kurlyk.view.startWindow.admin.StartAdminSceneCreator;
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

    @Autowired
    private StagePool stagePool;

    @Autowired
    private UsverInfo usverInfo;

    @Autowired
    private Communicator communicator;


    public void initialize(){
        logInAsGuestButton.setVisible(false);
        logInAsGuestButton.setOnAction(event -> {});

        loginField.textProperty().addListener((observable, oldValue, newValue) -> validateButtons());
        passwordField.textProperty().addListener((observable, oldValue, newValue) -> validateButtons());
        logInButton.setOnAction(event -> signIn());
    }

    private void validateButtons(){
        boolean loginFieldEmpty = loginField.getText().equals("");
        boolean passwordFieldEmpty = passwordField.getText().equals("");

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
            if (RoleTester.roleIsExist(usverInfo.getTokenDto(), RoleEnum.ADMIN.getName())) {
                stagePool.setSceneStage(Stages.START, new StartAdminSceneCreator().getScene());
            } else {
                stagePool.setSceneStage(Stages.START, new StartStudentSceneCreator().getScene());
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