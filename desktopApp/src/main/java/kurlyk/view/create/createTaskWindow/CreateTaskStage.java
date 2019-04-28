package kurlyk.view.create.createTaskWindow;

import kurlyk.view.common.stage.base.BaseStage;

public class CreateTaskStage extends BaseStage<CreateTaskController> {


    public CreateTaskStage() {
        super();
    }

    @Override
    public String getPathToMainStage() {
        return "create/CreateTaskWindow/main";
    }
}
