package kurlyk.view.task.matchingWindow;


import kurlyk.transfer.TaskDto;
import kurlyk.transfer.tasks.MatchingDto;
import kurlyk.view.common.stage.BaseSceneCreator;

public class MatchingSceneCreator extends BaseSceneCreator<MatchingController> {

    public MatchingSceneCreator(TaskDto taskDto, MatchingDto matchingDto, boolean editable) {
        super();
        controller.setItemsToView(taskDto, matchingDto, editable);
    }

    @Override
    public String getPathToMainStage() {
        return "task/matchingWindow/main";
    }
}
