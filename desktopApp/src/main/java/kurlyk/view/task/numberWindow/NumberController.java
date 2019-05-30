package kurlyk.view.task.numberWindow;


import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import kurlyk.communication.Communicator;
import kurlyk.models.Question;
import kurlyk.models.UserProgress;
import kurlyk.transfer.tasks.NumberDto;
import kurlyk.view.common.stage.StagePool;
import kurlyk.view.components.DoubleField;
import kurlyk.view.components.MyHtmlEditor;
import kurlyk.view.task.CommonTaskController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;


@Component
@Scope("prototype")
public class NumberController extends CommonTaskController<NumberDto> {

    @FXML private VBox root;
    @FXML private Button submit;
    @FXML private MyHtmlEditor textArea;
    @FXML private DoubleField inputField;

    @Autowired
    private Communicator communicator;

    @Autowired
    private StagePool stagePool;

    public void initialize(){

    }

    public void setQuestion(UserProgress userProgress, NumberDto numberDto, boolean editable, Consumer<Question> callbackAction) {
        final NumberDto rightNumberDto = numberDto;
        commonConfiguration(
                userProgress,
                () -> isRightAnswer(rightNumberDto, userProgress),
                editable,
                textArea,
                submit,
                communicator,
                stagePool,
                callbackAction
        );
        if (editable && numberDto.getNumber() != null) {
            inputField.setNumber(numberDto.getNumber());
        }
    }

    private Double isRightAnswer(NumberDto numberDto, UserProgress userProgress){
        double score = 0d;
        if (equalsTwoNumbers(numberDto.getNumber(), getResult().getNumber(), 6)){
            score = userProgress.getTask().getScore() * userProgress.getQuestion().getScore();
        }
        return score;
    }

    /*
        n - точность сравнения
        // Далее идёт код сверхразума.
        // Attention! Не пытайтесь разобраться!
     */
    public boolean equalsTwoNumbers(double value1, double value2, int n){
        for (int i = 0; i < n; i++){
            value1 *= 10;
            value2 *= 10;
        }
        long result1 = Math.round(value1);
        long result2 = Math.round(value2);

        if((result1 == result2) || (result1 + 1 == result2) || (result1 - 1 == result2)){
            return true;
        } else {
            return false;
        }
    }

    @Override
    public NumberDto getResult() {
        return new NumberDto(inputField.getNumber());
    }
}