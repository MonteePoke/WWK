package kurlyk.view.task.radioWindow;


import com.google.gson.Gson;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import kurlyk.common.Trio;
import kurlyk.communication.Communicator;
import kurlyk.communication.UsverInfo;
import kurlyk.model.Question;
import kurlyk.transfer.ResultAnswerDto;
import kurlyk.transfer.answer.SelectAnswerDto;
import kurlyk.transfer.tasks.SelectDto;
import kurlyk.view.common.stage.StagePool;
import kurlyk.view.common.stage.Stages;
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
    @FXML private Button createItemButton;
    @FXML private Button deleteItemButton;
    @FXML private HBox controlPanel;
    private Question question;
    private ToggleGroup group;

    @Autowired
    private Communicator communicator;

    @Autowired
    private StagePool stagePool;

    @Autowired
    private UsverInfo usverInfo;

    public void initialize(){
        group = new ToggleGroup();
        createItemButton.setOnAction(event -> {
            root.getChildren().add(new EditableRadioButton(false, "", 1, true, group));
        });
        deleteItemButton.setOnAction(event -> {
            try {
                root.getChildren().remove(root.getChildren().size() - 1);
            } catch (Exception ignored) {
            }
        });
    }

    public void setQuestion(Question question, boolean editable, Consumer<Question> callbackActionBefore, Consumer<Question> callbackActionAfter, Stages stageForClose) {
        this.question = question;
        SelectDto radioDto = new Gson().fromJson(question.getAnswer(), SelectDto.class);
        submitConfiguration(
                editable,
                question,
                submit,
                communicator,
                stagePool,
                callbackActionBefore,
                callbackActionAfter
        );
        setStageForClose(stageForClose);

        //Настройки работчего поля
        textArea.setDisable(!editable);
        textArea.setHtmlText(question.getQuestion());
        controlPanel.setVisible(editable);



        if (radioDto != null) {
            for (Trio<Boolean, String, Integer> option : radioDto.getOptions()){
                root.getChildren().add(
                        new EditableRadioButton(
                                option.getValueA(),
                                option.getValueB(),
                                option.getValueC(),
                                editable,
                                group
                        )
                );
            }
        } else {
            root.getChildren().add(new EditableRadioButton(false, "", 1, editable, group));
            root.getChildren().add(new EditableRadioButton(false, "", 1, editable, group));
            root.getChildren().add(new EditableRadioButton(false, "", 1, editable, group));
            root.getChildren().add(new EditableRadioButton(false, "", 1, editable, group));
        }
    }

    @Override
    public SelectDto getResult() {
        SelectDto resultDto = new SelectDto();
        for (Node node : root.getChildren()) {
            EditableRadioButton editableRadioButton = (EditableRadioButton) node;
            resultDto.getOptions().add(new Trio<>(
                    editableRadioButton.getRadioButton().isSelected(),
                    editableRadioButton.getHtmlEditor().getHtmlText(),
                    editableRadioButton.getCoefficientField().getValue()
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