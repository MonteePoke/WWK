package kurlyk.view.task.checkWindow;


import kurlyk.models.UserProgress;
import kurlyk.transfer.tasks.SelectDto;
import kurlyk.view.common.dto.BaseStageDto;
import kurlyk.view.common.stage.base.BaseSceneCreator;

public class CheckSceneCreator extends BaseSceneCreator<CheckController> {

    public CheckSceneCreator(UserProgress userProgress, SelectDto selectDto, boolean editable) {
        super(BaseStageDto.allOff());
        controller.setQuestion(userProgress, selectDto, editable);
    }

    @Override
    public String getPathToMainStage() {
        return "task/checkWindow/main";
    }
}
