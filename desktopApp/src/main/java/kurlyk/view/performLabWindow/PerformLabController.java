package kurlyk.view.performLabWindow;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.VBox;
import javafx.util.Pair;
import kurlyk.communication.Communicator;
import kurlyk.communication.UserInfo;
import kurlyk.transfer.TaskDto;
import kurlyk.transfer.tasks.SelectDto;
import kurlyk.view.common.controller.Controller;
import kurlyk.view.common.stage.StagePool;
import kurlyk.view.task.radioWindow.RadioSceneCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
@Scope("prototype")
public class PerformLabController extends Controller {

    @FXML private VBox root;

    @Autowired
    private StagePool stagePool;

    @Autowired
    private UserInfo userInfo;

    @Autowired
    private Communicator communicator;


    public void initialize(){
    }

    public void createQuestion(List<TaskDto> taskDtos){
        TabPane tabPane = new TabPane();
        for (TaskDto taskDto : taskDtos) {
            Tab tab = new Tab(taskDto.getName());

            Scene scene = null;
            switch (taskDto.getLabType()){
                case RADIO:
                    SelectDto radioDto = new SelectDto(Arrays.asList(
                            new Pair<>("", false),
                            new Pair<>("", true),
                            new Pair<>("", false),
                            new Pair<>("", false)
                    ));
                    scene = new RadioSceneCreator(taskDto, radioDto, false).getScene();
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
            tab.setContent(scene.getRoot());
            tabPane.getTabs().add(tab);
        }
        root.getChildren().add(tabPane);
    }
}