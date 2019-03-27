package kurlyk.view.task.numberWindow;


import com.google.gson.Gson;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import kurlyk.communication.Communicator;
import kurlyk.communication.UserProgress;
import kurlyk.transfer.QuestionDto;
import kurlyk.transfer.tasks.NumberDto;
import kurlyk.view.common.component.NumberField;
import kurlyk.view.common.controller.Controller;
import kurlyk.view.common.controller.TaskBodyController;
import kurlyk.view.common.stage.StagePool;
import kurlyk.view.common.stage.Stages;
import kurlyk.view.createLabWindow.CreateLabSceneCreator;
import kurlyk.view.utils.FxDialogs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.function.Supplier;


@Component
@Scope("prototype")
public class NumberController extends Controller implements TaskBodyController<NumberDto> {

    @FXML private VBox root;
    @FXML private Button submit;
    @FXML private TextArea textArea;
    @FXML private NumberField inputField;

    @Autowired
    private Communicator communicator;

    @Autowired
    private StagePool stagePool;

    @Autowired
    private UserProgress userProgress;

    public void initialize(){

    }

    public void setQuestion(QuestionDto questionDto, NumberDto numberDto, boolean editable) {
        final NumberDto rightNumberDto = numberDto;
        commonConfiguration(questionDto, () -> isRightAnswer(rightNumberDto), editable);
        if (editable && numberDto.getNumber() != null) {
            inputField.setNumber(numberDto.getNumber());
        }
    }

    private void commonConfiguration(QuestionDto questionDto, Supplier<Boolean> isRightAnswer, boolean editable) {
        textArea.setEditable(editable);
        if (editable){
            submit.setOnAction(event -> {
                questionDto.setQuestion(textArea.getText());
                questionDto.setAnswer(new Gson().toJson(getResult()));
                try {
                    communicator.postTask(questionDto);
                    stagePool.getStage(Stages.CREATE_LAB).setScene(new CreateLabSceneCreator().getScene());
                } catch (IOException e) {
                    FxDialogs.showError("", "Ошибка отправки данных");
                }
            });
        } else{
            textArea.setText(questionDto.getQuestion());
            submit.setOnAction(event -> {
                userProgress.getProgress().put(questionDto.getId(), isRightAnswer.get() ? 100 : 0);
                FxDialogs.showInformation("Результат", isRightAnswer.get() ? "Верно" : "Неверно");
            });
        }
    }

    public boolean isRightAnswer(NumberDto numberDto){
        return numberDto.equals(getResult());
    }

    @Override
    public NumberDto getResult() {
        return new NumberDto(inputField.getNumber());
    }
}