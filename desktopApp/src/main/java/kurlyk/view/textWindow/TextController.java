package kurlyk.view.textWindow;


import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import kurlyk.common.classesMadeByStas.StemmerPorterRU;
import kurlyk.transfer.TextDto;
import kurlyk.view.common.controller.Controller;
import kurlyk.view.common.controller.TaskBodyController;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


@Component
@Scope("prototype")
public class TextController extends Controller implements TaskBodyController<TextDto> {

    @FXML private TextField inputField;

    public void initialize(){

    }

    @Override
    public TextDto getResult() {
        return new TextDto(StemmerPorterRU.stemSentence(inputField.getText()));
    }
}