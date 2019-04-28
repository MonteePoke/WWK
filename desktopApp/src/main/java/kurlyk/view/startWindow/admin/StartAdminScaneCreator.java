package kurlyk.view.startWindow.admin;

import kurlyk.view.common.stage.base.BaseSceneCreator;

public class StartAdminScaneCreator extends BaseSceneCreator<StartAdminController> {


    public StartAdminScaneCreator() {
        super();
    }

    @Override
    public String getPathToMainStage() {
        return "startWindow/admin/main";
    }
}
