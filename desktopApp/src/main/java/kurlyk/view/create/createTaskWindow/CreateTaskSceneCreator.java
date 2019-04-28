package kurlyk.view.create.createTaskWindow;

import kurlyk.view.common.stage.base.BaseSceneCreator;

public class CreateTaskSceneCreator extends BaseSceneCreator<CreateTaskController> {


    public CreateTaskSceneCreator() {
        super();
    }

    @Override
    public String getPathToMainStage() {
        return "create/CreateTaskWindow/main";
    }
}
