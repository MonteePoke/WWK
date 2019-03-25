package kurlyk.view.task.matchingWindow;


import kurlyk.transfer.TaskDto;
import kurlyk.transfer.tasks.MatchingDto;
import kurlyk.view.common.stage.BaseStage;

public class MatchingStage extends BaseStage<MatchingController> {

    public MatchingStage(TaskDto taskDto, MatchingDto matchingDto, boolean editable) {
        super();
        controller.setItemsToView(taskDto, matchingDto, editable);
    }

    @Override
    public String getPathToMainStage() {
        return "task/matchingWindow/main";
    }

}
