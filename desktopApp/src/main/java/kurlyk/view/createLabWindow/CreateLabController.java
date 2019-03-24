package kurlyk.view.createLabWindow;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.util.Pair;
import kurlyk.LabType;
import kurlyk.communication.UserInfo;
import kurlyk.transfer.Role;
import kurlyk.transfer.TaskDto;
import kurlyk.transfer.tasks.SelectDto;
import kurlyk.view.common.controller.Controller;
import kurlyk.view.common.stage.StagePool;
import kurlyk.view.common.stage.Stages;
import kurlyk.view.task.radioWindow.RadioSceneCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Scope("prototype")
public class CreateLabController extends Controller {

    @FXML private TextField name;
    @FXML private ComboBox<Integer> labNumber;
    @FXML private ComboBox<LabType> labType;
    @FXML private Button further;


    @Autowired
    private StagePool stagePool;

    @Autowired
    private UserInfo userInfo;


    public void initialize(){
        boolean editable = userInfo.getTokenDto().getUserRole() == Role.ADMIN;
        labNumber.getItems().addAll(1, 2, 3, 4, 5, 6, 7);
        labType.getItems().addAll(
                LabType.GRAPH,
                LabType.FORMULA,
                LabType.TEXT,
                LabType.NUMBER,
                LabType.MATCHING,
                LabType.CHEK,
                LabType.RADIO
        );

        further.setOnAction(event -> {
            TaskDto taskDto = TaskDto.builder()
                    .name(name.getText())
                    .labNumber(labNumber.getValue())
                    .labType(labType.getValue())
                    .build();
            Scene scene = null;
            switch (taskDto.getLabType()){
                case RADIO:
                    SelectDto radioDto = new SelectDto(Arrays.asList(
                            new Pair<>("", false),
                            new Pair<>("", true),
                            new Pair<>("", false),
                            new Pair<>("", false)
                    ));
                    scene = new RadioSceneCreator(taskDto, radioDto, editable).getScene();
                    break;
                case CHEK:
                    break;
                case MATCHING:
                    break;
                case NUMBER:
                    break;
                case TEXT:
                    break;
                case FORMULA:
                    break;
                case GRAPH:
                    break;
            }
            stagePool.getStage(Stages.CREATE_LAB).setScene(scene);
        });
    }
}