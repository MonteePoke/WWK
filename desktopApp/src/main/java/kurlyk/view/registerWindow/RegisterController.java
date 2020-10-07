package kurlyk.view.registerWindow;

import com.google.common.primitives.Booleans;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import kurlyk.common.RoleTester;
import kurlyk.communication.Communicator;
import kurlyk.communication.UsverInfo;
import kurlyk.model.Usver;
import kurlyk.model.enums.RoleEnum;
import kurlyk.transfer.LoginDto;
import kurlyk.transfer.TokenDto;
import kurlyk.view.common.controller.Controller;
import kurlyk.view.common.stage.StagePool;
import kurlyk.view.common.stage.Stages;
import kurlyk.view.signInWindow.SignInStage;
import kurlyk.view.registerWindow.RegisterStage;
import kurlyk.view.startWindow.admin.StartAdminSceneCreator;
import kurlyk.view.startWindow.student.StartStudentSceneCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.ConnectException;

@Component
@Scope("prototype")
public class RegisterController extends Controller {

    @FXML public TextField loginField;
    @FXML private PasswordField passwordField;
    @FXML public TextField firstNameField;
    @FXML public TextField secondNameField;
    @FXML public TextField middleNameField;
    @FXML public TextField studyGroupField;
    @FXML private Button registerButton;
    @FXML private Label feedback;

    @Autowired
    private StagePool stagePool;

    @Autowired
    private UsverInfo usverInfo;

    @Autowired
    private Communicator communicator;


    public void initialize(){
        loginField.textProperty().addListener((observable, oldValue, newValue) -> validateButtons());
        passwordField.textProperty().addListener((observable, oldValue, newValue) -> validateButtons());
        firstNameField.textProperty().addListener((observable, oldValue, newValue) -> validateButtons());
        secondNameField.textProperty().addListener((observable, oldValue, newValue) -> validateButtons());
        middleNameField.textProperty().addListener((observable, oldValue, newValue) -> validateButtons());
        passwordField.textProperty().addListener((observable, oldValue, newValue) -> validateButtons());
        studyGroupField.textProperty().addListener((observable, oldValue, newValue) -> validateButtons());
        registerButton.setOnAction(event -> register());
    }

    private void validateButtons(){
        boolean loginFieldEmpty = loginField.getText().equals("");
        boolean passwordFieldEmpty = passwordField.getText().equals("");
        boolean firstNameFieldEmpty = firstNameField.getText().equals("");
        boolean secondNameFieldEmpty = secondNameField.getText().equals("");
        boolean middleNameFieldEmpty = middleNameField.getText().equals("");
        boolean studyGroupFieldEmpty = studyGroupField.getText().equals("");

        boolean[] flags = new boolean[] {loginFieldEmpty,
                passwordFieldEmpty,
                firstNameFieldEmpty,
                secondNameFieldEmpty,
                middleNameFieldEmpty,
                studyGroupFieldEmpty};

        if(Booleans.contains(flags, true))
            registerButton.setDisable(true);
        else
            registerButton.setDisable(false);
    }

    public void register(){
        Usver user = Usver.builder().
                login(loginField.getText()).
                password(passwordField.getText()).
                firstName(firstNameField.getText()).
                secondName(secondNameField.getText()).
                middleName(middleNameField.getText()).
                studyGroup(studyGroupField.getText()).build();
        try {
            if(!communicator.register(user)){
                feedback.setVisible(true);
                return;
            } else {
                feedback.setVisible(false);
            }
            stagePool.pushStage(Stages.SIGN_IN, new SignInStage());
            stagePool.showStage(Stages.SIGN_IN);
            stagePool.deleteStage(Stages.REGISTER);
        } catch (ConnectException e) {
            System.out.println("Нет соединения с сервером");
        } catch (IOException e){
            System.out.println("Ошибка регистрации");
        }
    }
}