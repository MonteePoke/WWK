package kurlyk.view.task.numberWindow;


import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import kurlyk.communication.Communicator;
import kurlyk.models.UserProgress;
import kurlyk.transfer.tasks.NumberDto;
import kurlyk.view.common.component.NumberField;
import kurlyk.view.common.stage.StagePool;
import kurlyk.view.task.CommonTaskController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


@Component
@Scope("prototype")
public class NumberController extends CommonTaskController<NumberDto> {

    @FXML private VBox root;
    @FXML private Button submit;
    @FXML private TextArea textArea;
    @FXML private NumberField inputField;

    @Autowired
    private Communicator communicator;

    @Autowired
    private StagePool stagePool;

    public void initialize(){

    }

    public void setQuestion(UserProgress userProgress, NumberDto numberDto, boolean editable) {
        final NumberDto rightNumberDto = numberDto;
        commonConfiguration(
                userProgress,
                () -> isRightAnswer(rightNumberDto, userProgress),
                editable,
                textArea,
                submit,
                communicator,
                stagePool
        );
        if (editable && numberDto.getNumber() != null) {
            inputField.setNumber(numberDto.getNumber());
        }
    }

    private Double isRightAnswer(NumberDto numberDto, UserProgress userProgress){
        double score = 0d;
        if (numberDto.equals(getResult())){
            score = userProgress.getTask().getScore() * userProgress.getQuestion().getScore();
        }
        return score;
    }

    @Override
    public NumberDto getResult() {
        return new NumberDto(inputField.getNumber());
    }
}