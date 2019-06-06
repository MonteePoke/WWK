package kurlyk.view.task.radioWindow;


import com.google.gson.Gson;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import javafx.util.Pair;
import kurlyk.communication.Communicator;
import kurlyk.models.Question;
import kurlyk.transfer.tasks.SelectDto;
import kurlyk.view.common.stage.StagePool;
import kurlyk.view.components.EditableRadioButton;
import kurlyk.view.components.MyHtmlEditor;
import kurlyk.view.task.CommonTaskController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.function.Consumer;


@Component
@Scope("prototype")
public class RadioController extends CommonTaskController<SelectDto> {

    @FXML private VBox root;
    @FXML private Button submit;
    @FXML private MyHtmlEditor textArea;

    @Autowired
    private Communicator communicator;

    @Autowired
    private StagePool stagePool;

    public void initialize(){
    }

    public void setQuestion(Question question, boolean editable, Consumer<Question> callbackAction) {
        SelectDto radioDto = new Gson().fromJson(question.getAnswer(), SelectDto.class)
        if(radioDto == null){
            radioDto = new SelectDto(Arrays.asList(
                    new Pair<>("", false),
                    new Pair<>("", false)
            ));
        }

        commonConfiguration(
                userProgress,
                editable,
                textArea,
                submit,
                communicator,
                stagePool,
                callbackAction
        );
        ToggleGroup group = new ToggleGroup();
        for (Pair<String, Boolean> option : radioDto.getOptions()){
            EditableRadioButton editableRadioButton = new EditableRadioButton(option.getKey(), editable);
            editableRadioButton.getRadioButton().setToggleGroup(group);
            root.getChildren().add(editableRadioButton);
        }
    }

    @Override
    public SelectDto getResult() {
        SelectDto selectDto = new SelectDto();
        for (Node node : root.getChildren()) {
            EditableRadioButton editableRadioButton = (EditableRadioButton) node;
            selectDto.getOptions().add(new Pair<>(editableRadioButton.getHtmlEditor().getHtmlText(),
                    editableRadioButton.getRadioButton().isSelected()
            ));
        }
        return selectDto;
    }
}