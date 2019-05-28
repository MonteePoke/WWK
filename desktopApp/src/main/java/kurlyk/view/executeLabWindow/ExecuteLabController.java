package kurlyk.view.executeLabWindow;

import com.google.gson.Gson;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import kurlyk.communication.Communicator;
import kurlyk.communication.UserInfo;
import kurlyk.models.UserProgress;
import kurlyk.transfer.tasks.*;
import kurlyk.view.common.controller.Controller;
import kurlyk.view.common.stage.StagePool;
import kurlyk.view.common.stage.base.BaseStage;
import kurlyk.view.components.UserProgressTab;
import kurlyk.view.showAnswerWindow.ShowAnswerStage;
import kurlyk.view.showResultWindow.ShowResultSceneCreator;
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
    private TabPane tabPane;
    private BaseStage stage;

    @Autowired
    private StagePool stagePool;

    @Autowired
    private UserInfo userInfo;

    @Autowired
    private Communicator communicator;


    public void initialize(){
    }

    public void setTasks(List<UserProgress> userProgresses, boolean isTest){
        tabPane = new TabPane();
        for (UserProgress userProgress : userProgresses) {
            UserProgressTab tab = new UserProgressTab("Вопрос №" + (tabPane.getTabs().size() + 1), userProgress);
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
        }
        if (userProgresses.size() > 0) {
            tabPane.getTabs().add(createResultTab(
                    userProgresses.get(0).getLabWork().getId(),
                    userProgresses.get(0).getUser().getId(),
                    isTest,
                    createStartLabCallback(),
                    createShowResultCallback()
            ));
        }
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        root.getChildren().add(tabPane);
    }

    private Tab createResultTab(Long labWorkId, Long userId, boolean isTest, Runnable startLabCallback, Runnable showResultCallback){
        Tab tab = new Tab("Результаты");
        tab.setContent(new ShowResultSceneCreator(labWorkId, userId, isTest, startLabCallback, showResultCallback).getScene().getRoot());
        return tab;
    }

    private Runnable createStartLabCallback(){
        return () ->{

        };
    }

    private Runnable createShowResultCallback(){
        return () ->{
            tabPane.getTabs().forEach(tab -> tab.setDisable(true));
            tabPane.getTabs().get(tabPane.getTabs().size() - 1).setDisable(false);
        };
    }

    private void createMenu(){
        stage.getMainMenu().getShowAnswerItem().setOnAction(event -> {
            try {
                ShowAnswerStage showAnswerStage = new ShowAnswerStage(
                        ((UserProgressTab) tabPane.getSelectionModel().getSelectedItem()).getUserProgress().getQuestion()
                );
                showAnswerStage.initOwner(this.stage);
                showAnswerStage.initModality(Modality.APPLICATION_MODAL);
                showAnswerStage.showAndWait();
            } catch (Exception ignored) {
            }
        });
    }

    public void setStage(BaseStage stage) {
        this.stage = stage;
        createMenu();
    }
}