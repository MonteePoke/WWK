package kurlyk.view.task.numberWindow;


import com.google.gson.Gson;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import kurlyk.communication.Communicator;
import kurlyk.communication.UsverInfo;
import kurlyk.model.Question;
import kurlyk.transfer.ResultAnswerDto;
import kurlyk.transfer.answer.NumberAnswerDto;
import kurlyk.transfer.tasks.NumberDto;
import kurlyk.view.common.stage.StagePool;
import kurlyk.view.common.stage.Stages;
import kurlyk.view.components.MyHtmlEditor;
import kurlyk.view.components.fields.DoubleField;
import kurlyk.view.components.fields.IntegerField;
import kurlyk.view.components.fields.SignDeterminant;
import kurlyk.view.task.SubmitConfigurationController;
import kurlyk.view.task.TabPurpose;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.function.Consumer;


@Component
@Scope("prototype")
public class NumberController extends SubmitConfigurationController<NumberDto> {

    @FXML private VBox root;
    @FXML private Button submit;
    @FXML private MyHtmlEditor textArea;
    @FXML private DoubleField inputField;
    @FXML private IntegerField accuracyField;
    private Question question;

    @Autowired
    private Communicator communicator;

    @Autowired
    private StagePool stagePool;

    @Autowired
    private UsverInfo usverInfo;

    public void initialize(){
        accuracyField.setSignDeterminant(SignDeterminant.POSITIVE_ONLY);
    }

    public void setQuestion(Question question, boolean editable, Consumer<Question> callbackActionBefore, Consumer<Question> callbackActionAfter, Stages stageForClose, TabPurpose tabPurpose) {
        this.question = question;
        NumberDto numberDto = new Gson().fromJson(question.getAnswer(), NumberDto.class);
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
        accuracyField.setVisible(editable);
        if (numberDto != null) {
            inputField.setValue(numberDto.getNumber());
            accuracyField.setValue(numberDto.getAccuracy());
        }
    }

    @Override
    public NumberDto getResult() {
        return new NumberDto(inputField.getValue(), accuracyField.getValue() != null ? accuracyField.getValue() : 1);
    }

    @Override
    public String getQuestionText() {
        return textArea.getHtmlText();
    }

    @Override
    public ResultAnswerDto getAnswerResult(Integer attempt) throws IOException {
        return communicator.testNumberAnswer(
                NumberAnswerDto
                        .builder()
                        .entity(getResult())
                        .usverId(usverInfo.getTokenDto().getUsverId())
                        .questionId(question.getId())
                        .attemptsNumber(attempt)
                        .build()
        );
    }
}