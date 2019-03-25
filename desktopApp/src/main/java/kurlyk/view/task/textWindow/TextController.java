package kurlyk.view.task.textWindow;


import com.google.gson.Gson;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import kurlyk.common.classesMadeByStas.StemmerPorterRU;
import kurlyk.communication.Communicator;
import kurlyk.communication.UserProgress;
import kurlyk.transfer.TaskDto;
import kurlyk.transfer.tasks.TextDto;
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
public class TextController extends Controller implements TaskBodyController<TextDto> {

    @FXML private VBox root;
    @FXML private Button submit;
    @FXML private TextArea textArea;
    @FXML private TextField inputField;

    @Autowired
    private Communicator communicator;

    @Autowired
    private StagePool stagePool;

    @Autowired
    private UserProgress userProgress;

    public void initialize(){

    }

    public void setQuestion(TaskDto taskDto, TextDto textDto, boolean editable) {
        final TextDto rightTextDto = textDto;
        commonConfiguration(taskDto, () -> isRightAnswer(rightTextDto), editable);
        if (editable && textDto.getText() != null) {
            inputField.setText(textDto.getText());
        }
    }

    private void commonConfiguration(TaskDto taskDto, Supplier<Boolean> isRightAnswer, boolean editable) {
        textArea.setEditable(editable);
        if (editable){
            submit.setOnAction(event -> {
                taskDto.setQuestion(textArea.getText());
                taskDto.setAnswer(new Gson().toJson(getResult()));
                try {
                    communicator.postTask(taskDto);
                    stagePool.getStage(Stages.CREATE_LAB).setScene(new CreateLabSceneCreator().getScene());
                } catch (IOException e) {
                    FxDialogs.showError("", "Ошибка отправки данных");
                }
            });
        } else{
            textArea.setText(taskDto.getQuestion());
            submit.setOnAction(event -> {
                userProgress.getProgress().put(taskDto.getId(), isRightAnswer.get() ? 100 : 0);
                FxDialogs.showInformation("Результат", isRightAnswer.get() ? "Верно" : "Неверно");
            });
        }
    }

    public boolean isRightAnswer(TextDto textDto){
        return textDto.equals(getResult());
    }

    @Override
    public TextDto getResult() {
        return new TextDto(StemmerPorterRU.stemSentence(inputField.getText()));
    }
}