package kurlyk.view.task.matchingWindow;


import kurlyk.transfer.QuestionDto;
import kurlyk.transfer.tasks.MatchingDto;
import kurlyk.view.common.stage.BaseSceneCreator;

public class MatchingSceneCreator extends BaseSceneCreator<MatchingController> {

    public MatchingSceneCreator(QuestionDto questionDto, MatchingDto matchingDto, boolean editable) {
        super();
        controller.setItemsToView(questionDto, matchingDto, editable);
    }

    @Override
    public String getPathToMainStage() {
        return "task/matchingWindow/main";
    }
}
