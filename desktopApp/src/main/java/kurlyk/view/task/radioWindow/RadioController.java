package kurlyk.view.task.radioWindow;


import com.google.gson.Gson;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import javafx.util.Pair;
import kurlyk.communication.Communicator;
import kurlyk.communication.UserProgress;
import kurlyk.transfer.TaskDto;
import kurlyk.transfer.tasks.SelectDto;
import kurlyk.view.common.component.EditableRadioButton;
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
public class RadioController extends Controller implements TaskBodyController<SelectDto> {

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

        ToggleGroup group = new ToggleGroup();
        for (Pair<String, Boolean> question : selectDto.getQuestions()){
            EditableRadioButton editableRadioButton = new EditableRadioButton(question.getKey(), editable);
            editableRadioButton.getRadioButton().setToggleGroup(group);
            root.getChildren().add(editableRadioButton);
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
            EditableRadioButton editableRadioButton = (EditableRadioButton) node;
            selectDto.getQuestions().add(new Pair<>(editableRadioButton.getHtmlEditor().getHtmlText(),
                    editableRadioButton.getRadioButton().isSelected()
            ));
        }
        return selectDto;
    }
}