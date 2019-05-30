package kurlyk.view.task.matchingWindow;


import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import kurlyk.communication.Communicator;
import kurlyk.models.Question;
import kurlyk.models.UserProgress;
import kurlyk.transfer.tasks.MatchingDto;
import kurlyk.view.common.stage.StagePool;
import kurlyk.view.components.DraggingListView;
import kurlyk.view.components.MyHtmlEditor;
import kurlyk.view.task.CommonTaskController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.function.Consumer;


@Component
@Scope("prototype")
public class MatchingController extends CommonTaskController<MatchingDto> {

    @FXML private VBox root;
    @FXML private Button submit;
    @FXML private MyHtmlEditor textArea;
    @FXML private DraggingListView leftField;
    @FXML private DraggingListView rightField;

    @Autowired
    private Communicator communicator;

    @Autowired
    private StagePool stagePool;


    public void initialize(){
    }

    public void setItemsToView(UserProgress userProgress, MatchingDto matchingDto, boolean editable, Consumer<Question> callbackAction){
        final MatchingDto rightMatchingDto = matchingDto;
        commonConfiguration(
                userProgress,
                () -> isRightAnswer(rightMatchingDto, userProgress),
                editable,
                textArea,
                submit,
                communicator,
                stagePool,
                callbackAction
        );
        if(!editable){
            matchingDto = getMixMatching(matchingDto);
        }
        leftField.getItems().addAll(matchingDto.getLeftPart());
        leftField.setEditable(editable);
        rightField.getItems().addAll(matchingDto.getRightPart());
        rightField.setEditable(editable);
    }

    private Double isRightAnswer(MatchingDto matchingDto, UserProgress userProgress){
        double score = 0d;
        if (matchingDto.equals(getResult())){
            score = userProgress.getTask().getScore() * userProgress.getQuestion().getScore();
        }
        return score;
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