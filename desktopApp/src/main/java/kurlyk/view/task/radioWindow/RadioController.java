package kurlyk.view.task.radioWindow;


import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import javafx.util.Pair;
import kurlyk.communication.Communicator;
import kurlyk.models.UserProgress;
import kurlyk.transfer.tasks.SelectDto;
import kurlyk.view.common.component.EditableRadioButton;
import kurlyk.view.common.component.MyHTMLEditor;
import kurlyk.view.common.stage.StagePool;
import kurlyk.view.task.CommonTaskController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


@Component
@Scope("prototype")
public class RadioController extends CommonTaskController<SelectDto> {

    @FXML private VBox root;
    @FXML private Button submit;
    @FXML private MyHTMLEditor textArea;

    @Autowired
    private Communicator communicator;

    @Autowired
    private StagePool stagePool;

    public void initialize(){
    }

    public void setQuestion(UserProgress userProgress, SelectDto selectDto, boolean editable) {
        final SelectDto rightSelectDto = selectDto;
        commonConfiguration(
                userProgress,
                () -> isRightAnswer(rightSelectDto, userProgress),
                editable,
                textArea,
                submit,
                communicator,
                stagePool
        );
        ToggleGroup group = new ToggleGroup();
        for (Pair<String, Boolean> option : selectDto.getOptions()){
            EditableRadioButton editableRadioButton = new EditableRadioButton(option.getKey(), editable);
            editableRadioButton.getRadioButton().setToggleGroup(group);
            root.getChildren().add(editableRadioButton);
        }
    }

    private Double isRightAnswer(SelectDto selectDto, UserProgress userProgress){
        double score = 0d;
        if (selectDto.equals(getResult())){
            score = userProgress.getTask().getScore() * userProgress.getQuestion().getScore();
        }
        return score;
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