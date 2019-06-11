package kurlyk.view.task.matchingWindow;


import com.google.gson.Gson;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import kurlyk.communication.Communicator;
import kurlyk.communication.UsverInfo;
import kurlyk.models.Question;
import kurlyk.transfer.ResultAnswerDto;
import kurlyk.transfer.answer.MatchingAnswerDto;
import kurlyk.transfer.tasks.MatchingDto;
import kurlyk.view.common.stage.StagePool;
import kurlyk.view.components.DraggingListView;
import kurlyk.view.components.MyHtmlEditor;
import kurlyk.view.task.SubmitConfigurationController;
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
    private Question question;

    @Autowired
    private Communicator communicator;

    @Autowired
    private StagePool stagePool;

    @Autowired
    private UsverInfo usverInfo;


    public void initialize(){
    }

    public void setItemsToView(Question question, boolean editable, Consumer<Long> callbackAction){
        this.question = question;
        MatchingDto matchingDto = new Gson().fromJson(question.getAnswer(), MatchingDto.class);
        submitConfiguration(
                editable,
                question,
                submit,
                communicator,
                stagePool,
                callbackAction
        );

        //Настройки работчего поля
        textArea.setDisable(!editable);
        textArea.setHtmlText(question.getQuestion());
        leftField.getItems().addAll(matchingDto.getLeftPart());
        leftField.setEditable(editable);
        rightField.getItems().addAll(matchingDto.getRightPart());
        rightField.setEditable(editable);
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