package kurlyk.view.numberWindow;


import javafx.fxml.FXML;
import kurlyk.view.common.component.NumberField;
import kurlyk.view.common.controller.Controller;
import kurlyk.view.common.controller.TaskBodyController;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


@Component
@Scope("prototype")
public class NumberController extends Controller implements TaskBodyController<Double> {

    @FXML private NumberField inputField;

    public void initialize(){

    }

    @Override
    public Double getResult() {
        return inputField.getNumber();
    }
}