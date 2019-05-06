package kurlyk.view.components.labTreeView.tree;

import kurlyk.view.common.dto.BaseStageDto;
import kurlyk.view.common.stage.base.BaseSceneCreator;

public class TreeSceneCreator extends BaseSceneCreator<TreeController> {

    public TreeSceneCreator() {
        super(BaseStageDto.allOff());
    }

    @Override
    public String getPathToMainStage() {
        return "components/labTreeView/tree/main";
    }
}
