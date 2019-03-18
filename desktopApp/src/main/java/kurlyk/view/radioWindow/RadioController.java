package kurlyk.view.radioWindow;


import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import javafx.util.Pair;
import kurlyk.transfer.SelectDto;
import kurlyk.view.common.component.EditableRadioButton;
import kurlyk.view.common.controller.Controller;
import kurlyk.view.common.controller.TaskBodyController;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


@Component
@Scope("prototype")
public class RadioController extends Controller implements TaskBodyController<SelectDto> {

    @FXML private VBox root;

    public void initialize(){
    }

    public void setQuestion(SelectDto selectDto, boolean editable) {
        ToggleGroup group = new ToggleGroup();
        for (Pair<String, Boolean> question : selectDto.getQuestions()){
            EditableRadioButton editableRadioButton = new EditableRadioButton(question.getKey(), editable);
            editableRadioButton.getRadioButton().setToggleGroup(group);
            root.getChildren().add(editableRadioButton);
        }
    }

    @Override
    public SelectDto getResult() {
        SelectDto selectDto = new SelectDto();
        for (Node node : root.getChildren()) {
            EditableRadioButton editableRadioButton = (EditableRadioButton) node;
            selectDto.getQuestions().add(new Pair<>(editableRadioButton.getHtmlEditor().getHtmlText(),
                    editableRadioButton.getRadioButton().isSelected()
            ));
        }
        return selectDto;
    }
}