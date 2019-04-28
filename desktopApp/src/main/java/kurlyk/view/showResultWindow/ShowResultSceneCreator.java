package kurlyk.view.showResultWindow;

import kurlyk.view.common.stage.base.BaseSceneCreator;

public class ShowResultSceneCreator extends BaseSceneCreator<ShowResultController> {


    public ShowResultSceneCreator(Long labWorkId, Long userId, boolean isTest, Runnable startLabCallback, Runnable showResultCallback) {
        super();
        controller.setResultOwner(labWorkId, userId, isTest, startLabCallback, showResultCallback);
    }

    @Override
    public String getPathToMainStage() {
        return "showResultWindow/main";
    }
}
