package kurlyk.view.task.checkWindow;


import com.google.gson.Gson;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.util.Pair;
import kurlyk.communication.Communicator;
import kurlyk.communication.UserProgress;
import kurlyk.transfer.TaskDto;
import kurlyk.transfer.tasks.SelectDto;
import kurlyk.view.common.component.EditableCheckBox;
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
public class CheckController extends Controller implements TaskBodyController<SelectDto> {

    @FXML private VBox root;
    @FXML private Button submit;
    @FXML private TextArea textArea;

    @Autowired
    private Communicator communicator;

    @Autowired
    private StagePool stagePool;

    @Autowired
    private UserProgress userProgress;

    public void initialize(){
    }

    public void setQuestion(TaskDto taskDto, SelectDto selectDto, boolean editable) {
        commonConfiguration(taskDto, () -> isRightAnswer(selectDto), editable);

        for (Pair<String, Boolean> question : selectDto.getQuestions()){
            root.getChildren().add(new EditableCheckBox(question.getKey(), editable));
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

    public boolean isRightAnswer(SelectDto selectDto){
        return selectDto.equals(getResult());
    }

    @Override
    public SelectDto getResult() {
        SelectDto selectDto = new SelectDto();
        for (Node node : root.getChildren()) {
            EditableCheckBox editableCheckBox = (EditableCheckBox) node;
            selectDto.getQuestions().add(new Pair<>(editableCheckBox.getHtmlEditor().getHtmlText(),
                    editableCheckBox.getCheckBox().isSelected()
            ));
        }
        return selectDto;
    }
}