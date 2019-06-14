package kurlyk.view.create.createQuestionWindow;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import kurlyk.QuestionType;
import kurlyk.common.Codable;
import kurlyk.communication.Communicator;
import kurlyk.communication.UsverInfo;
import kurlyk.model.Question;
import kurlyk.view.common.controller.Controller;
import kurlyk.view.common.stage.StagePool;
import kurlyk.view.common.stage.Stages;
import kurlyk.view.task.checkWindow.CheckSceneCreator;
import kurlyk.view.task.computerSystemDiagramWindow.ComputerSystemDiagramSceneCreator;
import kurlyk.view.task.formulaWindow.FormulaSceneCreator;
import kurlyk.view.task.matchingWindow.MatchingSceneCreator;
import kurlyk.view.task.numberWindow.NumberSceneCreator;
import kurlyk.view.task.radioWindow.RadioSceneCreator;
import kurlyk.view.task.sortingWindow.SortingSceneCreator;
import kurlyk.view.task.textWindow.TextSceneCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.function.Consumer;
import java.util.function.Supplier;

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
    private Supplier<Question> questionCreator;
    //null, если создаётся вопрос, не null, если редактируется вопрос
    private Long questionId;

    @Autowired
    private StagePool stagePool;

    @Autowired
    private UsverInfo usverInfo;

    @Autowired
    private Communicator communicator;


    public void initialize() {
        labType.getItems().addAll(
                QuestionType.COMPUTER_SYSTEM.getCode(),
                QuestionType.FORMULA.getCode(),
                QuestionType.TEXT.getCode(),
                QuestionType.NUMBER.getCode(),
                QuestionType.MATCHING.getCode(),
                QuestionType.CHECK.getCode(),
                QuestionType.RADIO.getCode(),
                QuestionType.SORTING.getCode()
        );

        further.setOnAction(event -> {
            try {
                Question question = questionId == null ? createQuestion() : communicator.getQuestion(questionId);
                Scene scene = null;
                switch (question.getQuestionType()) {
                    case RADIO:
                        scene = new RadioSceneCreator(question, true, callbackAction).getScene();
                        break;
                    case CHECK:
                        scene = new CheckSceneCreator(question, true, callbackAction).getScene();
                        break;
                    case SORTING:
                        scene = new SortingSceneCreator(question, true, callbackAction).getScene();
                        break;
                    case MATCHING:
                        scene = new MatchingSceneCreator(question, true, callbackAction).getScene();
                        break;
                    case NUMBER:
                        scene = new NumberSceneCreator(question, true, callbackAction).getScene();
                        break;
                    case TEXT:
                        scene = new TextSceneCreator(question, true, callbackAction).getScene();
                        break;
                    case FORMULA:
                        scene = new FormulaSceneCreator(question, true, callbackAction).getScene();
                        break;
                    case COMPUTER_SYSTEM:
                        scene = new ComputerSystemDiagramSceneCreator(question, true, callbackAction).getScene();
                        break;
                }
                stagePool.getStage(Stages.CREATE_QUESTION).setScene(scene);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private Question createQuestion() {
        Question question;
        if(questionCreator != null){
            question = questionCreator.get();
        } else{
            question = Question.builder().number(1).name("Вопрос № X").build();
        }
        question.setQuestionType(Codable.find(QuestionType.class, labType.getValue()));
        question.setAttemptsNumber(1);
        question.setScore(1L);
        question.setSkipQuestion(false);
        return question;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public void setQuestionConsumer(Consumer<Question> callbackAction) {
        this.callbackAction = callbackAction;
    }

    public void setQuestionCreator(Supplier<Question> questionCreator) {
        this.questionCreator = questionCreator;
    }
}