package kurlyk.view.task.matchingWindow;


import com.google.gson.Gson;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import kurlyk.communication.Communicator;
import kurlyk.communication.UsverInfo;
import kurlyk.model.Question;
import kurlyk.transfer.ResultAnswerDto;
import kurlyk.transfer.answer.MatchingAnswerDto;
import kurlyk.transfer.tasks.MatchingDto;
import kurlyk.view.common.stage.StagePool;
import kurlyk.view.common.stage.Stages;
import kurlyk.view.components.DraggingListView;
import kurlyk.view.components.MyHtmlEditor;
import kurlyk.view.task.SubmitConfigurationController;
import kurlyk.view.task.TabPurpose;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.function.Consumer;


@Component
@Scope("prototype")
public class MatchingController extends SubmitConfigurationController<MatchingDto> {

    @FXML private VBox root;
    @FXML private Button submit;
    @FXML private MyHtmlEditor textArea;
    @FXML private DraggingListView leftField;
    @FXML private DraggingListView rightField;
    @FXML private Button createItemButton;
    @FXML private Button deleteItemButton;
    @FXML private HBox controlPanel;
    private Question question;

    @Autowired
    private Communicator communicator;

    @Autowired
    private StagePool stagePool;

    @Autowired
    private UsverInfo usverInfo;


    public void initialize(){
        createItemButton.setOnAction(event -> {
            leftField.getItems().add("Новая");
            rightField.getItems().add("Новая");
        });
        deleteItemButton.setOnAction(event -> {
            try {
                leftField.getItems().remove(leftField.getItems().size()-1);
                rightField.getItems().remove(rightField.getItems().size()-1);
            } catch (Exception ignored) {
            }
        });
    }

    public void setItemsToView(Question question, boolean editable, Consumer<Question> callbackActionBefore, Consumer<Question> callbackActionAfter, Stages stageForClose, TabPurpose tabPurpose){
        this.question = question;
        MatchingDto matchingDto = new Gson().fromJson(question.getAnswer(), MatchingDto.class);
        submitConfiguration(
                editable,
                question,
                submit,
                communicator,
                stagePool,
                callbackActionBefore,
                callbackActionAfter,
                tabPurpose
        );
        setStageForClose(stageForClose);

        //Настройки работчего поля
        textArea.setDisable(!editable);
        textArea.setHtmlText(question.getQuestion());
        controlPanel.setVisible(editable);
        leftField.setEditable(editable);
        rightField.setEditable(editable);
        //Сшиваем scrollbar'ы обоих полей
        rightField.setBoundDraggingListView(leftField);
        rightField.setScrollBarDisabled(true);
        if (matchingDto != null) {
            leftField.getItems().addAll(matchingDto.getLeftPart());
            rightField.getItems().addAll(matchingDto.getRightPart());
        }
    }

    @Override
    public MatchingDto getResult() {
        return new MatchingDto(leftField.getItems(), rightField.getItems());
    }

    @Override
    public String getQuestionText() {
        return textArea.getHtmlText();
    }

    @Override
    public ResultAnswerDto getAnswerResult(Integer attempt) throws IOException {
        return communicator.testMatchingAnswer(
                MatchingAnswerDto
                        .builder()
                        .entity(getResult())
                        .usverId(usverInfo.getTokenDto().getUsverId())
                        .questionId(question.getId())
                        .attemptsNumber(attempt)
                        .build()
        );
    }
}