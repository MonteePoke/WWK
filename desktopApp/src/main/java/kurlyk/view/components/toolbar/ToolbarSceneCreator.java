package kurlyk.view.components.toolbar;

import kurlyk.view.common.stage.base.BaseSceneCreator;

public class ToolbarSceneCreator extends BaseSceneCreator<ToolbarController> {


    public ToolbarSceneCreator() {
        super(false);
    }

    @Override
    public String getPathToMainStage() {
        return "components/toolbar/main";
    }
}
