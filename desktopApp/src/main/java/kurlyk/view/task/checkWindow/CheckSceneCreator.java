package kurlyk.view.task.checkWindow;


import kurlyk.models.Question;
import kurlyk.models.UserProgress;
import kurlyk.transfer.tasks.SelectDto;
import kurlyk.view.common.dto.BaseStageDto;
import kurlyk.view.common.stage.base.BaseSceneCreator;

import java.util.function.Consumer;

public class CheckSceneCreator extends BaseSceneCreator<CheckController> {

    public CheckSceneCreator(UserProgress userProgress, SelectDto selectDto, boolean editable, Consumer<Question> callbackAction) {
        super(BaseStageDto.allOff());
        controller.setQuestion(userProgress, selectDto, editable, callbackAction);
    }

    @Override
    public String getPathToMainStage() {
        return "task/checkWindow/main";
    }
}
