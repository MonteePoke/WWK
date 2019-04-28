package kurlyk.view.task.radioWindow;


import kurlyk.transfer.tasks.SelectDto;
import kurlyk.view.common.stage.base.BaseStage;

public class RadioStage extends BaseStage<RadioController> {

    public RadioStage(SelectDto selectDto) {
        super();
//        controller.setQuestion(new Question(), selectDto, false);
    }

    @Override
    public String getPathToMainStage() {
        return "task/radioWindow/main";
    }
}
