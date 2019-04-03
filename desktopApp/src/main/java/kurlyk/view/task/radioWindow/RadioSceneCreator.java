package kurlyk.view.task.radioWindow;

import kurlyk.models.UserProgress;
import kurlyk.transfer.tasks.SelectDto;
import kurlyk.view.common.stage.BaseSceneCreator;

public class RadioSceneCreator extends BaseSceneCreator<RadioController> {


    public RadioSceneCreator(UserProgress userProgress, SelectDto selectDto, boolean editable) {
        super();
        controller.setQuestion(userProgress, selectDto, editable);
    }

    @Override
    public String getPathToMainStage() {
        return "task/radioWindow/main";
    }
}
