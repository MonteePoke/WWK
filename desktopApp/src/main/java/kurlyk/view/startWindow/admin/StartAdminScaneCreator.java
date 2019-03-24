package kurlyk.view.startWindow.admin;

import kurlyk.view.common.stage.BaseSceneCreator;

public class StartAdminScaneCreator extends BaseSceneCreator<StartAdminController> {


    public StartAdminScaneCreator() {
        super();
    }

    @Override
    public String getPathToMainStage() {
        return "startWindow/admin/main";
    }
}
