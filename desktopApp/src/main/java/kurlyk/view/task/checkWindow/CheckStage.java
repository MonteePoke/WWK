package kurlyk.view.task.checkWindow;


import kurlyk.transfer.tasks.SelectDto;
import kurlyk.view.common.stage.base.BaseStage;

public class CheckStage extends BaseStage<CheckController> {

    public CheckStage(SelectDto selectDto) {
        super();
//        controller.setQuestion(new Question(), selectDto, true);
    }

    @Override
    public String getPathToMainStage() {
        return "task/checkWindow/main";
    }
}