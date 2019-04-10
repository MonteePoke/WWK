package kurlyk.view.create.commonCreateWindow;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import kurlyk.communication.UserInfo;
import kurlyk.view.common.controller.Controller;
import kurlyk.view.common.stage.StagePool;
import kurlyk.view.common.stage.Stages;
import kurlyk.view.create.createLabWorkWindow.CreateLabWorkStage;
import kurlyk.view.create.createQuestionWindow.CreateQuestionStage;
import kurlyk.view.create.createTaskWindow.CreateTaskStage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class CommonCreateController extends Controller {

    @FXML Button createLabWork;
    @FXML Button createTask;
    @FXML Button createQuestion;

    @Autowired
    private StagePool stagePool;

    @Autowired
    private UserInfo userInfo;


    public void initialize() {
        createLabWork.setOnAction(event -> {
            stagePool.pushStageAndShow(Stages.CREATE_LAB_WORK, new CreateLabWorkStage());
        });
        createTask.setOnAction(event -> {
            stagePool.pushStageAndShow(Stages.CREATE_TEST, new CreateTaskStage());
        });
        createQuestion.setOnAction(event -> {
            stagePool.pushStageAndShow(Stages.CREATE_QUESTION, new CreateQuestionStage());
        });
    }
}