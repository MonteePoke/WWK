package kurlyk.view.textWindow;


import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import kurlyk.common.classesMadeByStas.StemmerPorterRU;
import kurlyk.view.common.controller.Controller;
import kurlyk.view.common.controller.TaskBodyController;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


@Component
@Scope("prototype")
public class TextController extends Controller implements TaskBodyController<String> {

    @FXML private TextField inputField;

    public void initialize(){

    }

    @Override
    public String getResult() {
        return StemmerPorterRU.stemSentence(inputField.getText());
    }
}