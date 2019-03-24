package kurlyk.view.createLabWindow;

import kurlyk.view.common.stage.BaseSceneCreator;

public class CreateLabSceneCreator extends BaseSceneCreator<CreateLabController> {


    public CreateLabSceneCreator() {
        super();
    }

    @Override
    public String getPathToMainStage() {
        return "createLabWindow/main";
    }
}
