package kurlyk.view.task.textWindow;


import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import kurlyk.common.classesMadeByStas.StemmerPorterRU;
import kurlyk.communication.Communicator;
import kurlyk.models.UserProgress;
import kurlyk.transfer.tasks.TextDto;
import kurlyk.view.common.component.MyHtmlEditor;
import kurlyk.view.common.stage.StagePool;
import kurlyk.view.task.CommonTaskController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


@Component
@Scope("prototype")
public class TextController extends CommonTaskController<TextDto> {

    @FXML private VBox root;
    @FXML private Button submit;
    @FXML private MyHtmlEditor textArea;
    @FXML private TextField inputField;

    @Autowired
    private Communicator communicator;

    @Autowired
    private StagePool stagePool;

    public void initialize(){

    }

    public void setQuestion(UserProgress userProgress, TextDto textDto, boolean editable) {
        final TextDto rightTextDto = textDto;
        commonConfiguration(
                userProgress,
                () -> isRightAnswer(rightTextDto, userProgress),
                editable,
                textArea,
                submit,
                communicator,
                stagePool
        );
        if (editable && textDto.getText() != null) {
            inputField.setText(textDto.getText());
        }
    }


    private Double isRightAnswer(TextDto textDto, UserProgress userProgress){
        double score = 0d;
        for (String possibleAnswer : textDto.getText().split(";")) {
            if(StemmerPorterRU.stemSentence(getResult().getText()).equals(StemmerPorterRU.stemSentence(possibleAnswer))){
                score = userProgress.getTask().getScore() * userProgress.getQuestion().getScore();
            }
        }
        return score;
    }

    @Override
    public TextDto getResult() {
        return new TextDto(inputField.getText());
    }
}