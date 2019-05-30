package kurlyk.view.task.checkWindow;


import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.util.Pair;
import kurlyk.communication.Communicator;
import kurlyk.models.Question;
import kurlyk.models.UserProgress;
import kurlyk.transfer.tasks.SelectDto;
import kurlyk.view.common.stage.StagePool;
import kurlyk.view.components.EditableCheckBox;
import kurlyk.view.components.MyHtmlEditor;
import kurlyk.view.task.CommonTaskController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;


@Component
@Scope("prototype")
public class CheckController extends CommonTaskController<SelectDto> {

    @FXML private VBox root;
    @FXML private Button submit;
    @FXML private MyHtmlEditor textArea;

    @Autowired
    private Communicator communicator;

    @Autowired
    private StagePool stagePool;

    public void initialize(){
    }

    public void setQuestion(UserProgress userProgress, SelectDto selectDto, boolean editable, Consumer<Question> callbackAction) {
        final SelectDto rightSelectDto = selectDto;
        commonConfiguration(
                userProgress,
                () -> isRightAnswer(rightSelectDto, userProgress),
                editable,
                textArea,
                submit,
                communicator,
                stagePool,
                callbackAction
        );

        for (Pair<String, Boolean> option : selectDto.getOptions()){
            root.getChildren().add(new EditableCheckBox(option.getKey(), editable));
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
            EditableCheckBox editableCheckBox = (EditableCheckBox) node;
            selectDto.getOptions().add(new Pair<>(editableCheckBox.getHtmlEditor().getHtmlText(),
                    editableCheckBox.getCheckBox().isSelected()
            ));
        }
        return selectDto;
    }
}