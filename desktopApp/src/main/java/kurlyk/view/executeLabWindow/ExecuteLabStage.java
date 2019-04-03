package kurlyk.view.executeLabWindow;

import kurlyk.models.UserProgress;
import kurlyk.view.common.stage.BaseStage;

import java.util.List;

public class ExecuteLabStage extends BaseStage<ExecuteLabController> {


    public ExecuteLabStage(List<UserProgress> userProgresses) {
        super();
        controller.setTasks(userProgresses);
    }

    @Override
    public String getPathToMainStage() {
        return "executeLabWindow/main";
    }
}
