package kurlyk.view.taskWindow;

import kurlyk.view.common.stage.BaseStage;

public class TaskStage extends BaseStage<TaskController> {


    public TaskStage() {
        super();
    }

    @Override
    public String getPathToMainStage() {
        return "signInWindow/main";
    }
}
