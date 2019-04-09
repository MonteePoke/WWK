package kurlyk.view.startWindow.admin;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import kurlyk.communication.Communicator;
import kurlyk.create.createQuestionWindow.CreateQuestionStage;
import kurlyk.view.common.controller.Controller;
import kurlyk.view.common.stage.StagePool;
import kurlyk.view.common.stage.Stages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class StartAdminController extends Controller {


    @FXML private Button create;

    @Autowired
    private StagePool stagePool;

    @Autowired
    private Communicator communicator;


    public void initialize(){
        create.setOnAction(event -> {
            stagePool.pushStageAndShow(Stages.CREATE_LAB, new CreateQuestionStage());
            stagePool.deleteStage(Stages.START);
        });
    }
}