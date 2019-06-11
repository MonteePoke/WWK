package kurlyk.view.task.radioWindow;


import com.google.gson.Gson;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import javafx.util.Pair;
import kurlyk.communication.Communicator;
import kurlyk.communication.UsverInfo;
import kurlyk.models.Question;
import kurlyk.transfer.ResultAnswerDto;
import kurlyk.transfer.answer.SelectAnswerDto;
import kurlyk.transfer.tasks.SelectDto;
import kurlyk.view.common.stage.StagePool;
import kurlyk.view.components.EditableRadioButton;
import kurlyk.view.components.MyHtmlEditor;
import kurlyk.view.task.SubmitConfigurationController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.function.Consumer;


@Component
@Scope("prototype")
public class RadioController extends SubmitConfigurationController<SelectDto> {

    @FXML private VBox root;
    @FXML private Button submit;
    @FXML private MyHtmlEditor textArea;
    private Question question;

    @Autowired
    private Communicator communicator;

    @Autowired
    private StagePool stagePool;

    @Autowired
    private UsverInfo usverInfo;

    public void initialize(){
    }

    public void setQuestion(Question question, boolean editable, Consumer<Long> callbackAction) {
        this.question = question;
        SelectDto radioDto = new Gson().fromJson(question.getAnswer(), SelectDto.class);
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
        ToggleGroup group = new ToggleGroup();
        for (Pair<String, Boolean> option : radioDto.getOptions()){
            EditableRadioButton editableRadioButton = new EditableRadioButton(option.getKey(), option.getValue(), editable);
            editableRadioButton.getRadioButton().setToggleGroup(group);
            root.getChildren().add(editableRadioButton);
        }
    }

    @Override
    public SelectDto getResult() {
        SelectDto resultDto = new SelectDto();
        for (Node node : root.getChildren()) {
            EditableRadioButton editableRadioButton = (EditableRadioButton) node;
            resultDto.getOptions().add(new Pair<>(editableRadioButton.getHtmlEditor().getHtmlText(),
                    editableRadioButton.getRadioButton().isSelected()
            ));
        }
        return resultDto;
    }

    @Override
    public String getQuestionText() {
        return textArea.getHtmlText();
    }

    @Override
    public ResultAnswerDto getAnswerResult(Integer attempt) throws IOException {
        return communicator.testRadioAnswer(
                SelectAnswerDto
                        .builder()
                        .entity(getResult())
                        .usverId(usverInfo.getTokenDto().getUsverId())
                        .questionId(question.getId())
                        .attemptsNumber(attempt)
                        .build()
        );
    }
}