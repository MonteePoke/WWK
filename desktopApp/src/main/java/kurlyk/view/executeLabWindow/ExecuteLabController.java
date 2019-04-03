package kurlyk.view.executeLabWindow;

import com.google.gson.Gson;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.VBox;
import kurlyk.communication.Communicator;
import kurlyk.communication.UserInfo;
import kurlyk.models.UserProgress;
import kurlyk.transfer.tasks.*;
import kurlyk.view.common.controller.Controller;
import kurlyk.view.common.stage.StagePool;
import kurlyk.view.task.checkWindow.CheckSceneCreator;
import kurlyk.view.task.computerSystemDiagramWindow.ComputerSystemDiagramSceneCreator;
import kurlyk.view.task.formulaWindow.FormulaSceneCreator;
import kurlyk.view.task.matchingWindow.MatchingSceneCreator;
import kurlyk.view.task.numberWindow.NumberSceneCreator;
import kurlyk.view.task.radioWindow.RadioSceneCreator;
import kurlyk.view.task.textWindow.TextSceneCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Scope("prototype")
public class ExecuteLabController extends Controller {

    @FXML private VBox root;

    @Autowired
    private StagePool stagePool;

    @Autowired
    private UserInfo userInfo;

    @Autowired
    private Communicator communicator;


    public void initialize(){
    }

    public void setTasks(List<UserProgress> userProgresses){
        TabPane tabPane = new TabPane();
        for (UserProgress userProgress : userProgresses) {
            Tab tab = new Tab("Вопрос №" + (tabPane.getTabs().size() + 1));
            Scene scene = null;
            switch (userProgress.getQuestion().getQuestionType()){
                case RADIO:
                    SelectDto radioDto = new Gson().fromJson(userProgress.getQuestion().getAnswer(), SelectDto.class);
                    scene = new RadioSceneCreator(userProgress, radioDto, false).getScene();
                    break;
                case CHEK:
                    SelectDto checkDto = new Gson().fromJson(userProgress.getQuestion().getAnswer(), SelectDto.class);
                    scene = new CheckSceneCreator(userProgress, checkDto, false).getScene();
                    break;
                case MATCHING:
                    MatchingDto matchingDto = new Gson().fromJson(userProgress.getQuestion().getAnswer(), MatchingDto.class);
                    scene = new MatchingSceneCreator(userProgress, matchingDto, false).getScene();
                    break;
                case NUMBER:
                    NumberDto numberDto = new Gson().fromJson(userProgress.getQuestion().getAnswer(), NumberDto.class);
                    scene = new NumberSceneCreator(userProgress, numberDto, false).getScene();
                    break;
                case TEXT:
                    TextDto textDto = new Gson().fromJson(userProgress.getQuestion().getAnswer(), TextDto.class);
                    scene = new TextSceneCreator(userProgress, textDto, false).getScene();
                    break;
                case FORMULA:
                    FormulaDto formulaDto = new Gson().fromJson(userProgress.getQuestion().getAnswer(), FormulaDto.class);
                    scene = new FormulaSceneCreator(userProgress, formulaDto, false).getScene();
                    break;
                case COMPUTER_SYSTEM:
                    ComputerSystemDto computerSystemDto = new Gson().fromJson(userProgress.getQuestion().getAnswer(), ComputerSystemDto.class);
                    scene = new ComputerSystemDiagramSceneCreator(userProgress, computerSystemDto, false).getScene();
                    break;
            }
            tab.setContent(scene.getRoot());
            tabPane.getTabs().add(tab);
            tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        }
        root.getChildren().add(tabPane);
    }
}