package kurlyk.view.startWindow.student;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import kurlyk.communication.Communicator;
import kurlyk.communication.UserInfo;
import kurlyk.models.*;
import kurlyk.view.common.controller.Controller;
import kurlyk.view.common.stage.StagePool;
import kurlyk.view.common.stage.Stages;
import kurlyk.view.components.FxDialogs;
import kurlyk.view.executeLabWindow.ExecuteLabStage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@Scope("prototype")
public class StartStudentController extends Controller {

    @FXML private ComboBox<LabWork> labNumber;
    @FXML private Button further;


    @Autowired
    private StagePool stagePool;

    @Autowired
    private Communicator communicator;

    @Autowired
    private UserInfo userInfo;


    public void initialize(){
        try {
            labNumber.getItems().addAll(communicator.getLabWorks());
        } catch (IOException e) {
            e.printStackTrace();
        }
        further.setOnAction(event -> {

            Map<Boolean, List<UserProgress>> userProgresses = getUserProgresses(labNumber.getValue());
            stagePool.pushStage(Stages.PERFORM_LAB, new ExecuteLabStage(userProgresses.get(false), false));
            stagePool.pushStageAndShow(Stages.PERFORM_TEST, new ExecuteLabStage(userProgresses.get(true), true));
            stagePool.closeStage(Stages.START);
        });
    }

    private Map<Boolean, List<UserProgress>> getUserProgresses(LabWork labWork){
        Map<Boolean, List<UserProgress>> userProgresses = new HashMap<>();
        try{
            Map<Boolean, List<Task>> tasks = communicator.getTasks(labWork)
                    .stream()
                    .collect(Collectors.partitioningBy(Task::getIsTest));
            List<UserProgress> userProgressLabs = createUserProgressFromGarbage(
                    tasks.get(false),
                    labWork,
                    userInfo.getTokenDto().toUser()
            );
            List<UserProgress> userProgressTests = createUserProgressFromGarbage(
                    tasks.get(true),
                    labWork,
                    userInfo.getTokenDto().toUser()
            );
            userProgresses.put(false, userProgressLabs);
            userProgresses.put(true, userProgressTests);
        } catch (IOException e) {
            FxDialogs.showError("", "Ошибка отправки данных");
        }
        return userProgresses;
    }

    private List<UserProgress> createUserProgressFromGarbage(List<Task> tasks, LabWork labWork, User user) throws IOException{
        List<UserProgress> userProgresses = new ArrayList<>();
        for (Task task : tasks) {
            for (Question question : communicator.getQuestions(task)) {
                userProgresses.add(
                        UserProgress.builder()
                                .question(question)
                                .task(task)
                                .labWork(labWork)
                                .user(user)
                                .build()
                );
            }
        }
        return userProgresses;
    }
}