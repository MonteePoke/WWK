package kurlyk.view.startWindow.student;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import kurlyk.communication.Communicator;
import kurlyk.communication.ExecuteMaster;
import kurlyk.communication.UsverInfo;
import kurlyk.models.LabWork;
import kurlyk.view.common.controller.Controller;
import kurlyk.view.common.stage.StagePool;
import kurlyk.view.common.stage.Stages;
import kurlyk.view.executeLabWindow.ExecuteLabStage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.IOException;

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

    @Autowired
    private ExecuteMaster executeMaster;


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
            executeMaster.initWork(
                    labNumber.getValue(),
                    variantNumber.getValue(),

            );
            stagePool.pushStage(Stages.PERFORM_LAB, new ExecuteLabStage(userProgresses.get(false), false));
            stagePool.pushStageAndShow(Stages.PERFORM_TEST, new ExecuteLabStage(userProgresses.get(true), true));
            stagePool.closeStage(Stages.START);
        });
    }
}