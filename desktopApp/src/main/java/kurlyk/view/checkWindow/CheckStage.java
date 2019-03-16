package kurlyk.view.checkWindow;


import kurlyk.transfer.CheckDto;
import kurlyk.view.common.stage.BaseStage;

public class CheckStage extends BaseStage<CheckController> {

    public CheckStage(CheckDto checkDto) {
        super();
        controller.setQuestion(checkDto, false);
    }

    @Override
    public String getPathToMainStage() {
        return "checkWindow/main";
    }
}
