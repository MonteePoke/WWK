package kurlyk.view.executeLabWindow;

import kurlyk.view.common.stage.base.BaseStage;


public class ExecuteLabStage extends BaseStage<ExecuteLabController> {
    public ExecuteLabStage(boolean isTest) {
        super();
        controller.setTasks(isTest);
        controller.setStage(this);
    }

    @Override
    public String getPathToMainStage() {
        return "executeLabWindow/main";
    }
}
