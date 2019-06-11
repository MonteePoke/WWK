package kurlyk.view.task.sortingWindow;


import com.google.gson.Gson;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import kurlyk.communication.Communicator;
import kurlyk.communication.UsverInfo;
import kurlyk.models.Question;
import kurlyk.transfer.ResultAnswerDto;
import kurlyk.transfer.answer.SortingAnswerDto;
import kurlyk.transfer.tasks.SortingDto;
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
public class SortingController extends SubmitConfigurationController<SortingDto> {

    @FXML private VBox root;
    @FXML private Button submit;
    @FXML private MyHtmlEditor textArea;
    @FXML private DraggingListView itemsField;
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
        SortingDto sortingDto = new Gson().fromJson(question.getAnswer(), SortingDto.class);
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
        itemsField.getItems().addAll(sortingDto.getItems());
        itemsField.setEditable(editable);
    }

    @Override
    public SortingDto getResult() {
        return new SortingDto(itemsField.getItems());
    }

    @Override
    public String getQuestionText() {
        return textArea.getHtmlText();
    }

    @Override
    public ResultAnswerDto getAnswerResult(Integer attempt) throws IOException {
        return communicator.testSortingAnswer(
                SortingAnswerDto
                        .builder()
                        .entity(getResult())
                        .usverId(usverInfo.getTokenDto().getUsverId())
                        .questionId(question.getId())
                        .attemptsNumber(attempt)
                        .build()
        );
    }
}