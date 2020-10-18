package kurlyk.view.task.matchingWindow;


import kurlyk.model.Question;
import kurlyk.view.common.dto.BaseStageDto;
import kurlyk.view.common.stage.Stages;
import kurlyk.view.common.stage.base.BaseSceneCreator;
import kurlyk.view.task.TabPurpose;

import java.util.function.Consumer;

public class MatchingSceneCreator extends BaseSceneCreator<MatchingController> {

    public MatchingSceneCreator(Question question, boolean editable, Consumer<Question> callbackActionBefore, Consumer<Question> callbackActionAfter, Stages stageForClose, TabPurpose tabPurpose) {
        super(BaseStageDto.allOff());
        controller.setItemsToView(question, editable, callbackActionBefore, callbackActionAfter, stageForClose, tabPurpose);
    }

    @Override
    public String getPathToMainStage() {
        return "task/matchingWindow/main";
    }
}
