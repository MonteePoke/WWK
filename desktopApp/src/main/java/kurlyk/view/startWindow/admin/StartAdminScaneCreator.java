package kurlyk.view.startWindow.admin;

import kurlyk.view.common.dto.BaseStageDto;
import kurlyk.view.common.stage.base.BaseSceneCreator;

public class StartAdminScaneCreator extends BaseSceneCreator<StartAdminController> {


    public StartAdminScaneCreator() {
        super(BaseStageDto.builder().needMenu(true).needTree(false).build());
    }

    @Override
    public String getPathToMainStage() {
        return "startWindow/admin/main";
    }
}
