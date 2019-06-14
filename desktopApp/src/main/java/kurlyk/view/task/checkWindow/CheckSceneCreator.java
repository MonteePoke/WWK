package kurlyk.view.task.checkWindow;


import kurlyk.model.Question;
import kurlyk.view.common.dto.BaseStageDto;
import kurlyk.view.common.stage.Stages;
import kurlyk.view.common.stage.base.BaseSceneCreator;

import java.util.function.Consumer;

public class CheckSceneCreator extends BaseSceneCreator<CheckController> {

    public CheckSceneCreator(Question question, boolean editable, Consumer<Question> callbackActionBefore, Consumer<Question> callbackActionAfter, Stages stageForClose) {
        super(BaseStageDto.allOff());
        controller.setQuestion(question, editable, callbackActionBefore, callbackActionAfter, stageForClose);
    }

    @Override
    public String getPathToMainStage() {
        return "task/checkWindow/main";
    }
}
