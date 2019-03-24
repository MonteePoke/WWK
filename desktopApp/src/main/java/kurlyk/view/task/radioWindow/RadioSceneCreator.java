package kurlyk.view.task.radioWindow;

import kurlyk.transfer.TaskDto;
import kurlyk.transfer.tasks.SelectDto;
import kurlyk.view.common.stage.BaseSceneCreator;

public class RadioSceneCreator extends BaseSceneCreator<RadioController> {


    public RadioSceneCreator(TaskDto taskDto, SelectDto selectDto, boolean editable) {
        super();
        controller.setQuestion(taskDto, selectDto, editable);
    }

    @Override
    public String getPathToMainStage() {
        return "task/radioWindow/main";
    }
}
