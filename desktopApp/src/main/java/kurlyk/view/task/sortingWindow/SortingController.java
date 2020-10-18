package kurlyk.view.task.sortingWindow;


import com.google.gson.Gson;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import kurlyk.communication.Communicator;
import kurlyk.communication.UsverInfo;
import kurlyk.model.Question;
import kurlyk.transfer.ResultAnswerDto;
import kurlyk.transfer.answer.SortingAnswerDto;
import kurlyk.transfer.tasks.SortingDto;
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
public class SortingController extends SubmitConfigurationController<SortingDto> {

    @FXML private VBox root;
    @FXML private Button submit;
    @FXML private MyHtmlEditor textArea;
    @FXML private DraggingListView itemsField;
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
            itemsField.getItems().add("Новая");
        });
        deleteItemButton.setOnAction(event -> {
            try {
                itemsField.getItems().remove(itemsField.getItems().size()-1);
            } catch (Exception ignored) {
            }
        });
    }

    public void setItemsToView(Question question, boolean editable, Consumer<Question> callbackActionBefore, Consumer<Question> callbackActionAfter, Stages stageForClose, TabPurpose tabPurpose){
        this.question = question;
        SortingDto sortingDto = new Gson().fromJson(question.getAnswer(), SortingDto.class);
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
        itemsField.setEditable(editable);
        if (sortingDto != null) {
            itemsField.getItems().addAll(sortingDto.getItems());
        }
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