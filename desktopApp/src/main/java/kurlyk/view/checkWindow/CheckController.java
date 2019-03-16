package kurlyk.view.checkWindow;


import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.VBox;
import kurlyk.transfer.CheckDto;
import kurlyk.view.common.component.EditableCheckBox;
import kurlyk.view.common.controller.Controller;
import kurlyk.view.common.controller.TaskBodyController;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


@Component
@Scope("prototype")
public class CheckController extends Controller implements TaskBodyController<CheckDto> {

    @FXML private VBox root;

    public void initialize(){
    }

    public void setQuestion(CheckDto checkDto, boolean editable) {
        for (String question : checkDto.getQuestions()){
            root.getChildren().add(new EditableCheckBox(question, editable));
        }
    }

    @Override
    public CheckDto getResult() {
        CheckDto checkDto = new CheckDto();
        for (Node node : root.getChildren()) {
            EditableCheckBox editableCheckBox = (EditableCheckBox) node;
            checkDto.getQuestions().add(editableCheckBox.getTextField().getText());
            checkDto.getCorrectAnswers().add(editableCheckBox.getCheckBox().isSelected());
        }
        return checkDto;
    }
}