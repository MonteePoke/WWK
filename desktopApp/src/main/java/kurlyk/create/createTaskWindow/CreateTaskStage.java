package kurlyk.create.createTaskWindow;

import kurlyk.view.common.stage.BaseStage;

public class CreateTaskStage extends BaseStage<CreateTaskController> {


    public CreateTaskStage() {
        super();
    }

    @Override
    public String getPathToMainStage() {
        return "create/CreateTaskWindow/main";
    }
}
