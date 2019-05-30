package kurlyk.view.create.createQuestionWindow;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.util.Pair;
import kurlyk.QuestionType;
import kurlyk.communication.UserInfo;
import kurlyk.models.LabWork;
import kurlyk.models.Question;
import kurlyk.models.Task;
import kurlyk.models.UserProgress;
import kurlyk.transfer.tasks.*;
import kurlyk.view.common.controller.Controller;
import kurlyk.view.common.stage.StagePool;
import kurlyk.view.common.stage.Stages;
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

import java.util.Arrays;
import java.util.function.Consumer;

@Component
@Scope("prototype")
public class CreateQuestionController extends Controller {

    @FXML private TextField nameField;
    @FXML private ComboBox<QuestionType> labType;
    @FXML private Button further;
    private Consumer<Question> callbackAction;

    @Autowired
    private StagePool stagePool;

    @Autowired
    private UserInfo userInfo;


    public void initialize(){
        labType.getItems().addAll(
                QuestionType.COMPUTER_SYSTEM,
                QuestionType.FORMULA,
                QuestionType.TEXT,
                QuestionType.NUMBER,
                QuestionType.MATCHING,
                QuestionType.CHEK,
                QuestionType.RADIO
        );

        further.setOnAction(event -> {
            UserProgress userProgress = createUserProgress();
            Scene scene = null;
            switch (userProgress.getQuestion().getQuestionType()){
                case RADIO:
                    SelectDto radioDto = new SelectDto(Arrays.asList(
                            new Pair<>("", false),
                            new Pair<>("", false),
                            new Pair<>("", false),
                            new Pair<>("", false)
                    ));
                    scene = new RadioSceneCreator(userProgress, radioDto, true, callbackAction).getScene();
                    break;
                case CHEK:
                    SelectDto chekDto = new SelectDto(Arrays.asList(
                            new Pair<>("", false),
                            new Pair<>("", false),
                            new Pair<>("", false),
                            new Pair<>("", false)
                    ));
                    scene = new CheckSceneCreator(userProgress, chekDto, true, callbackAction).getScene();
                    break;
                case MATCHING:
                    MatchingDto matchingDto = new MatchingDto(
                            Arrays.asList("", "", "", ""),
                            Arrays.asList("", "", "", "")
                    );
                    scene = new MatchingSceneCreator(userProgress, matchingDto, true, callbackAction).getScene();
                    break;
                case NUMBER:
                    NumberDto numberDto = new NumberDto();
                    scene = new NumberSceneCreator(userProgress, numberDto, true, callbackAction).getScene();
                    break;
                case TEXT:
                    TextDto textDto = new TextDto();
                    scene = new TextSceneCreator(userProgress, textDto, true, callbackAction).getScene();
                    break;
                case FORMULA:
                    FormulaDto formulaDto = new FormulaDto();
                    scene = new FormulaSceneCreator(userProgress, formulaDto, true, callbackAction).getScene();
                    break;
                case COMPUTER_SYSTEM:
                    ComputerSystemDto computerSystemDto = new ComputerSystemDto();
                    scene = new ComputerSystemDiagramSceneCreator(userProgress, computerSystemDto, true, callbackAction).getScene();
                    break;
            }
            stagePool.getStage(Stages.CREATE_QUESTION).setScene(scene);
        });
    }

    private UserProgress createUserProgress(){
        LabWork labWork = LabWork.builder()
                .build();
        Task task = Task.builder()
                .isTest(false)
                .score(1d)
                .build();
        Question question = Question.builder()
                .questionType(labType.getValue())
                .name(nameField.getText())
                .score(1d)
                .build();

        return UserProgress.builder()
                .labWork(labWork)
                .task(task)
                .question(question)
                .build();
    }

    public void setQuestionConsumer(Consumer<Question> callbackAction){
        this.callbackAction = callbackAction;
    }
}