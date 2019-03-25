package kurlyk.view.task.checkWindow;


import kurlyk.transfer.TaskDto;
import kurlyk.transfer.tasks.SelectDto;
import kurlyk.view.common.stage.BaseSceneCreator;

public class CheckSceneCreator extends BaseSceneCreator<CheckController> {

    public CheckSceneCreator(TaskDto taskDto, SelectDto selectDto, boolean editable) {
        super();
        controller.setQuestion(taskDto, selectDto, editable);
    }

    @Override
    public String getPathToMainStage() {
        return "task/checkWindow/main";
    }
}
