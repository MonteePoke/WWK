package kurlyk.view.task.checkWindow;


import kurlyk.models.Question;
import kurlyk.view.common.dto.BaseStageDto;
import kurlyk.view.common.stage.base.BaseSceneCreator;

import java.util.function.Consumer;

public class CheckSceneCreator extends BaseSceneCreator<CheckController> {

    public CheckSceneCreator(Question question, boolean editable, Consumer<Long> callbackAction) {
        super(BaseStageDto.allOff());
        controller.setQuestion(question, editable, callbackAction);
    }

    @Override
    public String getPathToMainStage() {
        return "task/checkWindow/main";
    }
}
