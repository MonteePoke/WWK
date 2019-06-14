package kurlyk.view.task.matchingWindow;


import kurlyk.model.Question;
import kurlyk.view.common.dto.BaseStageDto;
import kurlyk.view.common.stage.Stages;
import kurlyk.view.common.stage.base.BaseSceneCreator;

import java.util.function.Consumer;

public class MatchingSceneCreator extends BaseSceneCreator<MatchingController> {

    public MatchingSceneCreator(Question question, boolean editable, Consumer<Question> callbackActionBefore, Consumer<Question> callbackActionAfter, Stages stageForClose) {
        super(BaseStageDto.allOff());
        controller.setItemsToView(question, editable, callbackActionBefore, callbackActionAfter, stageForClose);
    }

    @Override
    public String getPathToMainStage() {
        return "task/matchingWindow/main";
    }
}
