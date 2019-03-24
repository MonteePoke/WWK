package kurlyk.view.signInWindow;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.util.Pair;
import kurlyk.communication.Communicator;
import kurlyk.communication.UserInfo;
import kurlyk.transfer.LoginDto;
import kurlyk.transfer.Role;
import kurlyk.transfer.tasks.SelectDto;
import kurlyk.view.common.controller.Controller;
import kurlyk.view.common.stage.StagePool;
import kurlyk.view.common.stage.Stages;
import kurlyk.view.startWindow.StartStage;
import kurlyk.view.startWindow.admin.StartAdminScaneCreator;
import kurlyk.view.startWindow.student.StartStudentSceneCreator;
import kurlyk.view.task.checkWindow.CheckStage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.ConnectException;
import java.util.Arrays;

@Component
@Scope("prototype")
public class SignInController extends Controller {

    @FXML public TextField loginInput;
    @FXML private PasswordField passwordInput;
    @FXML private Button guestRunButton;
    @FXML private Button userRunButton;

    @Autowired
    private StagePool stagePool;

    @Autowired
    private UserInfo userInfo;

    @Autowired
    private Communicator communicator;


    public void initialize(){

        guestRunButton.setOnAction(event -> {
//            stagePool.pushStageAndShow(Stages.COMPUTER_SYSTEM, new ComputerSystemDiagramStage());
//            stagePool.pushStageAndShow(Stages.FORMULA, new FormulaStage());
//            stagePool.pushStageAndShow(Stages.TEXT, new TextStage());
//            stagePool.pushStageAndShow(Stages.NUMBER, new NumberStage());
//            MatchingDto matchingDto = new MatchingDto(Arrays.asList("111", "222", "333"), Arrays.asList("111", "222", "333"));
//            stagePool.pushStageAndShow(Stages.MATCHING, new MatchingStage(matchingDto));
            SelectDto checkDto = new SelectDto(Arrays.asList(new Pair<>("a1", true), new Pair<>("b2", false), new Pair<>("c3", false), new Pair<>("d4", true)));
            stagePool.pushStageAndShow(Stages.CHECK, new CheckStage(checkDto));
//            SelectDto radioDto = new SelectDto(Arrays.asList(new Pair<>("a1", false), new Pair<>("b2", true), new Pair<>("c3", false), new Pair<>("d4", false)));
//            stagePool.pushStageAndShow(Stages.RADIO, new RadioStage(radioDto));
            stagePool.closeStage(Stages.SIGN_IN);
        });

        userRunButton.setOnAction(event -> {
            LoginDto loginDto = LoginDto
                    .builder()
//                    .login(loginInput.getText())
//                    .password(passwordInput.getText())
//                    .login("admin")
//                    .password("admin")
                    .login("student")
                    .password("student")
                    .build();

            try {
                communicator.login(loginDto);
                stagePool.pushStage(Stages.START, new StartStage());
                if (userInfo.getTokenDto().getUserRole() == Role.ADMIN) {
                    stagePool.getStage(Stages.START).setScene(new StartAdminScaneCreator().getScene());
                } else {
                    stagePool.getStage(Stages.START).setScene(new StartStudentSceneCreator().getScene());
                }
                stagePool.showStage(Stages.START);
                stagePool.closeStage(Stages.SIGN_IN);
            } catch (ConnectException e) {
                System.out.println("Нет соединения с сервером");
            } catch (IOException e){
                System.out.println("Ошибка авторизации");
            }
        });
    }
}