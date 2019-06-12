package kurlyk.view.showResultWindow;

import kurlyk.transfer.ExecuteCallbackDto;
import kurlyk.view.common.dto.BaseStageDto;
import kurlyk.view.common.stage.base.BaseSceneCreator;

public class ShowResultSceneCreator extends BaseSceneCreator<ShowResultController> {


    public ShowResultSceneCreator(ExecuteCallbackDto executeCallbackDto, Runnable furtherCallback) {
        super(BaseStageDto.allOff());
        controller.setResult(executeCallbackDto, furtherCallback);
    }

    @Override
    public String getPathToMainStage() {
        return "showResultWindow/main";
    }
}
