package kurlyk.create.commonCreateWindow;

import kurlyk.view.common.stage.BaseSceneCreator;

public class CommonCreateSceneCreator extends BaseSceneCreator<CommonCreateController> {


    public CommonCreateSceneCreator() {
        super();
    }

    @Override
    public String getPathToMainStage() {
        return "create/commonCreateWindow/main";
    }
}
