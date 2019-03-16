package kurlyk.view.radioWindow;


import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import kurlyk.transfer.RadioDto;
import kurlyk.view.common.component.EditableRadioButton;
import kurlyk.view.common.controller.Controller;
import kurlyk.view.common.controller.TaskBodyController;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


@Component
@Scope("prototype")
public class RadioController extends Controller implements TaskBodyController<RadioDto> {

    @FXML private VBox root;

    public void initialize(){
    }

    public void setQuestion(RadioDto radioDto, boolean editable) {
        ToggleGroup group = new ToggleGroup();
        for (String question : radioDto.getQuestions()){
            EditableRadioButton editableRadioButton = new EditableRadioButton(question, editable);
            editableRadioButton.getRadioButton().setToggleGroup(group);
            root.getChildren().add(editableRadioButton);
        }
        //По умолчанию выбран первый
        ((EditableRadioButton) root.getChildren().get(0)).getRadioButton().fire();
    }

    @Override
    public RadioDto getResult() {
        RadioDto radioDto = new RadioDto();
        for (Node node : root.getChildren()) {
            EditableRadioButton editableRadioButton = (EditableRadioButton) node;
            radioDto.getQuestions().add(editableRadioButton.getTextField().getText());
            if(editableRadioButton.getRadioButton().isSelected()){
                //Порядковый номер только что добавленной штуки
                radioDto.setCorrectAnswer(radioDto.getQuestions().size() - 1);
            }
        }
        return radioDto;
    }
}