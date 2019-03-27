package kurlyk.view.performLabWindow;

import com.google.gson.Gson;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.VBox;
import kurlyk.communication.Communicator;
import kurlyk.communication.UserInfo;
import kurlyk.transfer.QuestionDto;
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

    public void createQuestion(List<QuestionDto> questionDtos){
        TabPane tabPane = new TabPane();
        for (QuestionDto questionDto : questionDtos) {
            Tab tab = new Tab(questionDto.getName());

            Scene scene = null;
            String content = questionDto.getAnswer();
            switch (questionDto.getQuestionType()){
                case RADIO:
                    SelectDto radioDto = new Gson().fromJson(content, SelectDto.class);
                    scene = new RadioSceneCreator(questionDto, radioDto, false).getScene();
                    break;
                case CHEK:
                    SelectDto chekDto = new Gson().fromJson(content, SelectDto.class);
                    scene = new CheckSceneCreator(questionDto, chekDto, false).getScene();
                    break;
                case MATCHING:
                    MatchingDto matchingDto = new Gson().fromJson(content, MatchingDto.class);
                    scene = new MatchingSceneCreator(questionDto, matchingDto, false).getScene();
                    break;
                case NUMBER:
                    NumberDto numberDto = new Gson().fromJson(content, NumberDto.class);
                    scene = new NumberSceneCreator(questionDto, numberDto, false).getScene();
                    break;
                case TEXT:
                    TextDto textDto = new Gson().fromJson(content, TextDto.class);
                    scene = new TextSceneCreator(questionDto, textDto, false).getScene();
                    break;
                case FORMULA:
                    FormulaDto formulaDto = new Gson().fromJson(content, FormulaDto.class);
                    scene = new FormulaSceneCreator(questionDto, formulaDto, false).getScene();
                    break;
                case COMPUTER_SYSTEM:
                    ComputerSystemDto computerSystemDto = new Gson().fromJson(content, ComputerSystemDto.class);
                    scene = new ComputerSystemDiagramSceneCreator(questionDto, computerSystemDto, false).getScene();
                    break;
            }
            tab.setContent(scene.getRoot());
            tabPane.getTabs().add(tab);
        }
        root.getChildren().add(tabPane);
    }
}