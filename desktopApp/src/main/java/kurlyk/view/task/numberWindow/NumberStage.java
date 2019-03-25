package kurlyk.view.task.numberWindow;


import kurlyk.transfer.TaskDto;
import kurlyk.transfer.tasks.NumberDto;
import kurlyk.view.common.stage.BaseStage;

public class NumberStage extends BaseStage<NumberController> {

    public NumberStage(TaskDto taskDto, NumberDto numberDto, boolean editable) {
        super();
        controller.setQuestion(taskDto, numberDto, editable);
    }

    @Override
    public String getPathToMainStage() {
        return "task/numberWindow/main";
    }
}
