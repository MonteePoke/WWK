package kurlyk.view.numberWindow;


import javafx.fxml.FXML;
import kurlyk.transfer.NumberDto;
import kurlyk.view.common.component.NumberField;
import kurlyk.view.common.controller.Controller;
import kurlyk.view.common.controller.TaskBodyController;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


@Component
@Scope("prototype")
public class NumberController extends Controller implements TaskBodyController<NumberDto> {

    @FXML private NumberField inputField;

    public void initialize(){

    }

    @Override
    public NumberDto getResult() {
        return new NumberDto(inputField.getNumber());
    }
}