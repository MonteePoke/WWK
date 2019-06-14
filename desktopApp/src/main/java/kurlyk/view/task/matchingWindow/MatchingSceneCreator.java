package kurlyk.view.task.matchingWindow;


import kurlyk.model.Question;
import kurlyk.view.common.dto.BaseStageDto;
import kurlyk.view.common.stage.base.BaseSceneCreator;

import java.util.function.Consumer;

public class MatchingSceneCreator extends BaseSceneCreator<MatchingController> {

    public MatchingSceneCreator(Question question, boolean editable, Consumer<Question> callbackAction) {
        super(BaseStageDto.allOff());
        controller.setItemsToView(question, editable, callbackAction);
    }

    @Override
    public String getPathToMainStage() {
        return "task/matchingWindow/main";
    }
}
