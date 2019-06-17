package kurlyk.view.startWindow.student;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import kurlyk.communication.Communicator;
import kurlyk.communication.ExecuteMaster;
import kurlyk.communication.UsverInfo;
import kurlyk.model.LabWork;
import kurlyk.view.common.controller.Controller;
import kurlyk.view.common.stage.StagePool;
import kurlyk.view.common.stage.Stages;
import kurlyk.view.executeLabWindow.ExecuteLabStage;
import kurlyk.view.showResultWindow.ShowResultSceneCreator;
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
        stagePool.pushStage(Stages.PERFORM_WORK, new Stage());
        further.setOnAction(event -> {
            executeMaster.initWork(
                    labNumber.getValue().getId(),
                    variantNumber.getValue(),
                    (executeCallbackDto -> stagePool.setSceneStage(
                            Stages.PERFORM_WORK,
                            new ShowResultSceneCreator(executeCallbackDto, () -> {
                                if (executeCallbackDto.getIsExecuted()){
                                    stagePool.setSceneStage(Stages.PERFORM_WORK, new ExecuteLabStage().getScene());
                                } else {
                                    stagePool.setSceneStage(Stages.START, new StartStudentSceneCreator().getScene());
                                    stagePool.showStage(Stages.START);
                                    stagePool.deleteStage(Stages.PERFORM_WORK);
                                }
                            }).getScene()
                    )),
                    (executeCallbackDto -> stagePool.setSceneStage(
                            Stages.PERFORM_WORK,
                            new ShowResultSceneCreator(executeCallbackDto, ()-> {
                                stagePool.setSceneStage(Stages.START, new StartStudentSceneCreator().getScene());
                                stagePool.showStage(Stages.START);
                                stagePool.deleteStage(Stages.PERFORM_WORK);
                            }).getScene()
                    ))
            );
            stagePool.closeStage(Stages.START);
            stagePool.pushStageAndShow(Stages.PERFORM_WORK, new ExecuteLabStage());
        });
    }
}