package kurlyk.view.task.matchingWindow;


import kurlyk.models.Question;
import kurlyk.transfer.tasks.MatchingDto;
import kurlyk.view.common.stage.base.BaseStage;

public class MatchingStage extends BaseStage<MatchingController> {

    public MatchingStage(Question question, MatchingDto matchingDto, boolean editable) {
        super();
//        controller.setItemsToView(question, matchingDto, editable);
    }

    @Override
    public String getPathToMainStage() {
        return "task/matchingWindow/main";
    }

}
