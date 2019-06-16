package kurlyk.view.startWindow.admin;

import kurlyk.view.common.dto.BaseStageDto;
import kurlyk.view.common.stage.base.BaseSceneCreator;

public class StartAdminSceneCreator extends BaseSceneCreator<StartAdminController> {


    public StartAdminSceneCreator() {
        super(BaseStageDto.builder().needMenu(true).build());
    }

    @Override
    public String getPathToMainStage() {
        return "startWindow/admin/main";
    }
}
