package kurlyk.view.checkWindow;


import kurlyk.transfer.SelectDto;
import kurlyk.view.common.stage.BaseStage;

public class CheckStage extends BaseStage<CheckController> {

    public CheckStage(SelectDto selectDto) {
        super();
        controller.setQuestion(selectDto, true);
    }

    @Override
    public String getPathToMainStage() {
        return "checkWindow/main";
    }
}
