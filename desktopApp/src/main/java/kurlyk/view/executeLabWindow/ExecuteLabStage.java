package kurlyk.view.executeLabWindow;

import kurlyk.view.common.stage.base.BaseStage;


public class ExecuteLabStage extends BaseStage<ExecuteLabController> {
    public ExecuteLabStage(List<UserProgress> userProgresses, boolean isTest) {
        super();
        controller.setTasks(userProgresses, isTest);
        controller.setStage(this);
    }

    @Override
    public String getPathToMainStage() {
        return "executeLabWindow/main";
    }
}
