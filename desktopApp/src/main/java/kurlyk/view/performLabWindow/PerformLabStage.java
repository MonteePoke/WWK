package kurlyk.view.performLabWindow;

import kurlyk.transfer.TaskDto;
import kurlyk.view.common.stage.BaseStage;

import java.util.List;

public class PerformLabStage extends BaseStage<PerformLabController> {


    public PerformLabStage(List<TaskDto> taskDtos) {
        super();
        controller.createQuestion(taskDtos);
    }

    @Override
    public String getPathToMainStage() {
        return "performLabWindow/main";
    }
}
