package kurlyk.view.radioWindow;


import kurlyk.transfer.SelectDto;
import kurlyk.view.common.stage.BaseStage;

public class RadioStage extends BaseStage<RadioController> {

    public RadioStage(SelectDto selectDto) {
        super();
        controller.setQuestion(selectDto, false);
    }

    @Override
    public String getPathToMainStage() {
        return "radioWindow/main";
    }
}
