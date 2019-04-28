package kurlyk.view.create.commonCreateWindow;

import kurlyk.view.common.stage.base.BaseSceneCreator;

public class CommonCreateSceneCreator extends BaseSceneCreator<CommonCreateController> {


    public CommonCreateSceneCreator() {
        super();
    }

    @Override
    public String getPathToMainStage() {
        return "create/commonCreateWindow/main";
    }
}
