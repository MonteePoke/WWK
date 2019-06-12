package kurlyk.view.executeLabWindow;

import kurlyk.view.common.stage.base.BaseStage;


public class ExecuteLabStage extends BaseStage<ExecuteLabController> {
    public ExecuteLabStage() {
        super();
        controller.setTasks();
        controller.setStage(this);
    }

    @Override
    public String getPathToMainStage() {
        return "executeLabWindow/main";
    }
}
