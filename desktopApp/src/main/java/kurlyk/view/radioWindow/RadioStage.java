package kurlyk.view.radioWindow;


import kurlyk.transfer.RadioDto;
import kurlyk.view.common.stage.BaseStage;

public class RadioStage extends BaseStage<RadioController> {

    public RadioStage(RadioDto radioDto) {
        super();
        controller.setQuestion(radioDto, false);
    }

    @Override
    public String getPathToMainStage() {
        return "radioWindow/main";
    }
}
