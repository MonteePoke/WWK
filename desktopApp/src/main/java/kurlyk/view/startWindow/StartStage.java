package kurlyk.view.startWindow;

import kurlyk.view.common.dto.BaseStageDto;
import kurlyk.view.common.stage.base.BaseStage;

public class StartStage extends BaseStage<StartController> {


    public StartStage() {
        super(BaseStageDto.allOff());
    }

    @Override
    public String getPathToMainStage() {
        return "";
    }
}
