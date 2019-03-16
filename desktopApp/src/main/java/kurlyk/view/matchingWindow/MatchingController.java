package kurlyk.view.matchingWindow;


import javafx.fxml.FXML;
import kurlyk.common.classesMadeByStas.DraggingListView;
import kurlyk.transfer.MatchingDto;
import kurlyk.view.common.controller.Controller;
import kurlyk.view.common.controller.TaskBodyController;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


@Component
@Scope("prototype")
public class MatchingController extends Controller implements TaskBodyController<MatchingDto> {

    @FXML private DraggingListView leftField;
    @FXML private DraggingListView rightField;

    public void initialize(){
    }

    public void setItemsToView(MatchingDto matchingDto){
        leftField.getItems().addAll(matchingDto.getLeftPart());
        rightField.getItems().addAll(matchingDto.getRightPart());
    }

    private MatchingDto extractAnswer(){
        return new MatchingDto(leftField.getItems(), rightField.getItems());
    }

    @Override
    public MatchingDto getResult() {
        return extractAnswer();
    }
}