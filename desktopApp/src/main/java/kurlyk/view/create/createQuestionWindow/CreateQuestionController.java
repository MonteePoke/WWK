package kurlyk.view.create.createQuestionWindow;

import javafx.fxml.FXML;
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
import kurlyk.view.components.CommonSceneCreator;
import kurlyk.view.task.TabPurpose;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

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
    @Getter @Setter private Consumer<Question> callbackActionBefore;
    @Getter @Setter private Consumer<Question> callbackActionAfter;
    @Getter @Setter private Supplier<Question> questionCreator;

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
            Question question = createQuestion();
            stagePool.getStage(Stages.CREATE_QUESTION).setScene(
                    CommonSceneCreator.questionSceneCreator(
                            question,
                            true,
                            callbackActionBefore,
                            callbackActionAfter,
                            Stages.CREATE_QUESTION,
                            TabPurpose.EDITOR
                    )
            );
        });
    }

    private Question createQuestion() {
        Question question;
        if(questionCreator != null){
            question = questionCreator.get();
            question.setName(nameField.getText());
        } else{
            question = Question.builder().number(1).name(nameField.getText()).build();
        }
        question.setQuestionType(Codable.find(QuestionType.class, labType.getValue()));
        question.setAttemptsNumber(1);
        question.setScore(1L);
        question.setSkipQuestion(false);
        return question;
    }
}