package kurlyk.view.task.checkWindow;


import com.google.gson.Gson;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
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
import kurlyk.view.components.EditableCheckBox;
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
public class CheckController extends SubmitConfigurationController<SelectDto> {

    @FXML private VBox root;
    @FXML private Button submit;
    @FXML private MyHtmlEditor textArea;
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
            root.getChildren().add(new EditableCheckBox(false, "", 1, true));
        });
        deleteItemButton.setOnAction(event -> {
            try {
                root.getChildren().remove(root.getChildren().size() - 1);
            } catch (Exception ignored) {
            }
        });
    }

    public void setQuestion(Question question, boolean editable, Consumer<Question> callbackActionBefore, Consumer<Question> callbackActionAfter, Stages stageForClose, TabPurpose tabPurpose) {
        this.question = question;
        SelectDto checkDto = new Gson().fromJson(question.getAnswer(), SelectDto.class);
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
        if (checkDto != null) {
            for (Trio<Boolean, String, Integer> option : checkDto.getOptions()){
                root.getChildren().add(new EditableCheckBox(option.getA(), option.getB(), option.getC(), editable));
            }
        } else {
            root.getChildren().add(new EditableCheckBox(false, "", 1, editable));
            root.getChildren().add(new EditableCheckBox(false, "", 1, editable));
            root.getChildren().add(new EditableCheckBox(false, "", 1, editable));
            root.getChildren().add(new EditableCheckBox(false, "", 1, editable));
        }
    }

    @Override
    public SelectDto getResult() {
        SelectDto selectDto = new SelectDto();
        for (Node node : root.getChildren()) {
            EditableCheckBox editableCheckBox = (EditableCheckBox) node;
            selectDto.getOptions().add(new Trio<>(
                    editableCheckBox.getCheckBox().isSelected(),
                    editableCheckBox.getHtmlEditor().getHtmlText(),
                    editableCheckBox.getCoefficientField().getValue()
            ));
        }
        return selectDto;
    }

    @Override
    public String getQuestionText() {
        return textArea.getHtmlText();
    }

    @Override
    public ResultAnswerDto getAnswerResult(Integer attempt) throws IOException {
        return communicator.testCheckAnswer(
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