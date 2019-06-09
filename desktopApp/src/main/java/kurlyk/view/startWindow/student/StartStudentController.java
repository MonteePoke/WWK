package kurlyk.view.startWindow.student;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import kurlyk.communication.Communicator;
import kurlyk.communication.UsverInfo;
import kurlyk.models.LabWork;
import kurlyk.models.Question;
import kurlyk.models.Task;
import kurlyk.models.Usver;
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
    @FXML private ComboBox<Integer> variantNumber;
    @FXML private Button further;


    @Autowired
    private StagePool stagePool;

    @Autowired
    private Communicator communicator;

    @Autowired
    private UsverInfo usverInfo;


    public void initialize(){
        try {
            labNumber.getItems().addAll(communicator.getLabWorks());
            labNumber.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                variantNumber.getItems().clear();
                for(int i = 0; i < (newValue.getVariantsNumber() == null ? 1 : newValue.getVariantsNumber()); i++){
                    variantNumber.getItems().add(i + 1);
                }
                variantNumber.getSelectionModel().select(0);
            });
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
        Map<Boolean, List<UserProgress>> usverProgresses = new HashMap<>();
        try{
            Map<Boolean, List<Task>> tasks = communicator.getTasks(labWork)
                    .stream()
                    .collect(Collectors.partitioningBy(Task::getIsTest));
            List<UserProgress> usverProgressLabs = createUserProgressFromGarbage(
                    tasks.get(false),
                    labWork,
                    usverInfo.getTokenDto().toUsver()
            );
            List<UserProgress> usverProgressTests = createUserProgressFromGarbage(
                    tasks.get(true),
                    labWork,
                    usverInfo.getTokenDto().toUsver()
            );
            usverProgresses.put(false, usverProgressLabs);
            usverProgresses.put(true, usverProgressTests);
        } catch (IOException e) {
            FxDialogs.showError("", "Ошибка отправки данных");
        }
        return usverProgresses;
    }

    private List<UserProgress> createUserProgressFromGarbage(List<Task> tasks, LabWork labWork, Usver usver) throws IOException{
        List<UserProgress> userProgresses = new ArrayList<>();
        for (Task task : tasks) {
            for (Question question : communicator.getQuestions(task)) {
                userProgresses.add(
                        UserProgress.builder()
                                .question(question)
                                .task(task)
                                .labWork(labWork)
                                .user(usver)
                                .build()
                );
            }
        }
        return userProgresses;
    }
}