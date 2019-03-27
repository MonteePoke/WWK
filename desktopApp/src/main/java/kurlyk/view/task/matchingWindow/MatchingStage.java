package kurlyk.view.task.matchingWindow;


import kurlyk.transfer.QuestionDto;
import kurlyk.transfer.tasks.MatchingDto;
import kurlyk.view.common.stage.BaseStage;

public class MatchingStage extends BaseStage<MatchingController> {

    public MatchingStage(QuestionDto questionDto, MatchingDto matchingDto, boolean editable) {
        super();
        controller.setItemsToView(questionDto, matchingDto, editable);
    }

    @Override
    public String getPathToMainStage() {
        return "task/matchingWindow/main";
    }

}
