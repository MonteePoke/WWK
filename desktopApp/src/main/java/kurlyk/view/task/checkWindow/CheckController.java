package kurlyk.view.task.checkWindow;


import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.util.Pair;
import kurlyk.transfer.tasks.SelectDto;
import kurlyk.view.common.component.EditableCheckBox;
import kurlyk.view.common.controller.Controller;
import kurlyk.view.common.controller.TaskBodyController;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


@Component
@Scope("prototype")
public class CheckController extends Controller implements TaskBodyController<SelectDto> {

    @FXML private VBox root;
    @FXML private Button button;

    public void initialize(){
        button.setOnAction(event -> getResult());
    }

    public void setQuestion(SelectDto selectDto, boolean editable) {
        for (Pair<String, Boolean> question : selectDto.getQuestions()){
            root.getChildren().add(new EditableCheckBox(question.getKey(), editable));
        }
    }

    @Override
    public SelectDto getResult() {
        SelectDto selectDto = new SelectDto();
        for (Node node : root.getChildren()) {
            EditableCheckBox editableCheckBox = (EditableCheckBox) node;
            selectDto.getQuestions().add(new Pair<>(editableCheckBox.getHtmlEditor().getHtmlText(),
                    editableCheckBox.getCheckBox().isSelected()
            ));
        }
        return selectDto;
    }
}