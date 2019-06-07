package kurlyk.view.create.createQuestionWindow;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.util.Pair;
import kurlyk.QuestionType;
import kurlyk.common.Codable;
import kurlyk.communication.UserInfo;
import kurlyk.models.Question;
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

    @FXML
    private TextField nameField;
    @FXML
    private ComboBox<String> labType;
    @FXML
    private Button further;
    private Consumer<Question> callbackAction;
    //null, если создаётся вопрос, не null, если редактируется вопрос
    private Long questionId;

    @Autowired
    private StagePool stagePool;

    @Autowired
    private UserInfo userInfo;


    public void initialize() {
        labType.getItems().addAll(
                QuestionType.COMPUTER_SYSTEM.getCode(),
                QuestionType.FORMULA.getCode(),
                QuestionType.TEXT.getCode(),
                QuestionType.NUMBER.getCode(),
                QuestionType.MATCHING.getCode(),
                QuestionType.CHECK.getCode(),
                QuestionType.RADIO.getCode()
        );

        further.setOnAction(event -> {
            Question question = createQuestion();
            Scene scene = null;
            switch (question.getQuestionType()) {
                case RADIO:
                    scene = new RadioSceneCreator(question, true, callbackAction).getScene();
                    break;
                case CHECK:
                    SelectDto chekDto = new SelectDto(Arrays.asList(
                            new Pair<>("", false),
                            new Pair<>("", false)
                    ));
                    scene = new CheckSceneCreator(question, true, callbackAction).getScene();
                    break;
                case MATCHING:
                    MatchingDto matchingDto = new MatchingDto(
                            Arrays.asList("", "", "", ""),
                            Arrays.asList("", "", "", "")
                    );
                    scene = new MatchingSceneCreator(question, true, callbackAction).getScene();
                    break;
                case NUMBER:
                    NumberDto numberDto = new NumberDto();
                    scene = new NumberSceneCreator(question, true, callbackAction).getScene();
                    break;
                case TEXT:
                    TextDto textDto = new TextDto();
                    scene = new TextSceneCreator(question, true, callbackAction).getScene();
                    break;
                case FORMULA:
                    FormulaDto formulaDto = new FormulaDto();
                    scene = new FormulaSceneCreator(question, true, callbackAction).getScene();
                    break;
                case COMPUTER_SYSTEM:
                    ComputerSystemDto computerSystemDto = new ComputerSystemDto();
                    scene = new ComputerSystemDiagramSceneCreator(question, true, callbackAction).getScene();
                    break;
            }
            stagePool.getStage(Stages.CREATE_QUESTION).setScene(scene);
        });
    }

    private Question createQuestion() {
        return Question.builder()
                .questionType(Codable.find(QuestionType.class, labType.getValue()))
                .number(1)
                .name(nameField.getText())
                .attemptsNumber(1)
                .score(1L)
                .skipQuestion(false)
                .build();
    }

    public void setQuestionConsumer(Consumer<Question> callbackAction) {
        this.callbackAction = callbackAction;
    }
}