package kurlyk.view.task.textWindow;


import com.google.gson.Gson;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import kurlyk.communication.Communicator;
import kurlyk.communication.UsverInfo;
import kurlyk.model.Question;
import kurlyk.transfer.ResultAnswerDto;
import kurlyk.transfer.answer.TextAnswerDto;
import kurlyk.transfer.tasks.TextDto;
import kurlyk.view.common.stage.StagePool;
import kurlyk.view.components.MyHtmlEditor;
import kurlyk.view.task.SubmitConfigurationController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.function.Consumer;


@Component
@Scope("prototype")
public class TextController extends SubmitConfigurationController<TextDto> {

    @FXML private VBox root;
    @FXML private Button submit;
    @FXML private MyHtmlEditor textArea;
    @FXML private TextField inputField;
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
        TextDto textDto = new Gson().fromJson(question.getAnswer(), TextDto.class);
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
        inputField.setText(textDto.getText());
    }

    @Override
    public TextDto getResult() {
        return new TextDto(inputField.getText());
    }

    @Override
    public String getQuestionText() {
        return textArea.getHtmlText();
    }

    @Override
    public ResultAnswerDto getAnswerResult(Integer attempt) throws IOException {
        return communicator.testTextAnswer(
                TextAnswerDto
                        .builder()
                        .entity(getResult())
                        .usverId(usverInfo.getTokenDto().getUsverId())
                        .questionId(question.getId())
                        .attemptsNumber(attempt)
                        .build()
        );
    }
}