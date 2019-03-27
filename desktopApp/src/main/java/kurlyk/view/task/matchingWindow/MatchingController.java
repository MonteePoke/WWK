package kurlyk.view.task.matchingWindow;


import com.google.gson.Gson;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import kurlyk.common.classesMadeByStas.DraggingListView;
import kurlyk.communication.Communicator;
import kurlyk.communication.UserProgress;
import kurlyk.transfer.QuestionDto;
import kurlyk.transfer.tasks.MatchingDto;
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
import java.util.Collections;
import java.util.function.Supplier;


@Component
@Scope("prototype")
public class MatchingController extends Controller implements TaskBodyController<MatchingDto> {

    @FXML private VBox root;
    @FXML private Button submit;
    @FXML private TextArea textArea;
    @FXML private DraggingListView leftField;
    @FXML private DraggingListView rightField;

    @Autowired
    private Communicator communicator;

    @Autowired
    private StagePool stagePool;

    @Autowired
    private UserProgress userProgress;


    public void initialize(){
    }

    public void setItemsToView(QuestionDto questionDto, MatchingDto matchingDto, boolean editable){
        final MatchingDto rightMatchingDto = matchingDto;
        commonConfiguration(questionDto, () -> isRightAnswer(rightMatchingDto), editable);
        if(!editable){
            matchingDto = getMixMatching(matchingDto);
        }

        leftField.getItems().addAll(matchingDto.getLeftPart());
        leftField.setEditable(editable);
        rightField.getItems().addAll(matchingDto.getRightPart());
        rightField.setEditable(editable);
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

    public boolean isRightAnswer(MatchingDto matchingDto){
        return matchingDto.equals(getResult());
    }

    private MatchingDto getMixMatching(MatchingDto matchingDto){
        MatchingDto newMatchingDto = new MatchingDto(matchingDto);
        Collections.shuffle(newMatchingDto.getLeftPart());
        Collections.shuffle(newMatchingDto.getRightPart());
        return newMatchingDto;
    }

    @Override
    public MatchingDto getResult() {
        return new MatchingDto(leftField.getItems(), rightField.getItems());
    }
}