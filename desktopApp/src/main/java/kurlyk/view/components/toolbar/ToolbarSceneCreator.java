package kurlyk.view.components.toolbar;

import kurlyk.view.common.dto.BaseStageDto;
import kurlyk.view.common.stage.base.BaseSceneCreator;

public class ToolbarSceneCreator extends BaseSceneCreator<ToolbarController> {


    public ToolbarSceneCreator() {
        super(BaseStageDto.allOff());
    }

    @Override
    public String getPathToMainStage() {
        return "components/toolbar/main";
    }
}
