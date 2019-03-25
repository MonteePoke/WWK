package kurlyk.view.task.checkWindow;


import kurlyk.transfer.TaskDto;
import kurlyk.transfer.tasks.SelectDto;
import kurlyk.view.common.stage.BaseStage;

public class CheckStage extends BaseStage<CheckController> {

    public CheckStage(SelectDto selectDto) {
        super();
        controller.setQuestion(new TaskDto(), selectDto, true);
    }

    @Override
    public String getPathToMainStage() {
        return "task/checkWindow/main";
    }
}
