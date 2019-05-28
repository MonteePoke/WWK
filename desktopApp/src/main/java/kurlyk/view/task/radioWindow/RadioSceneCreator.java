package kurlyk.view.task.radioWindow;

import kurlyk.models.UserProgress;
import kurlyk.transfer.tasks.SelectDto;
import kurlyk.view.common.dto.BaseStageDto;
import kurlyk.view.common.stage.base.BaseSceneCreator;

public class RadioSceneCreator extends BaseSceneCreator<RadioController> {


    public RadioSceneCreator(UserProgress userProgress, SelectDto selectDto, boolean editable) {
        super(BaseStageDto.allOff());
        controller.setQuestion(userProgress, selectDto, editable);
    }

    @Override
    public String getPathToMainStage() {
        return "task/radioWindow/main";
    }
}
