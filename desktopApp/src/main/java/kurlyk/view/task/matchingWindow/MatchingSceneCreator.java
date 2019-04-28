package kurlyk.view.task.matchingWindow;


import kurlyk.models.UserProgress;
import kurlyk.transfer.tasks.MatchingDto;
import kurlyk.view.common.stage.base.BaseSceneCreator;

public class MatchingSceneCreator extends BaseSceneCreator<MatchingController> {

    public MatchingSceneCreator(UserProgress userProgress, MatchingDto matchingDto, boolean editable) {
        super();
        controller.setItemsToView(userProgress, matchingDto, editable);
    }

    @Override
    public String getPathToMainStage() {
        return "task/matchingWindow/main";
    }
}
