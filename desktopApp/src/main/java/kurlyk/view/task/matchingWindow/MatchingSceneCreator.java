package kurlyk.view.task.matchingWindow;


import kurlyk.models.Question;
import kurlyk.models.UserProgress;
import kurlyk.transfer.tasks.MatchingDto;
import kurlyk.view.common.dto.BaseStageDto;
import kurlyk.view.common.stage.base.BaseSceneCreator;

import java.util.function.Consumer;

public class MatchingSceneCreator extends BaseSceneCreator<MatchingController> {

    public MatchingSceneCreator(UserProgress userProgress, MatchingDto matchingDto, boolean editable, Consumer<Question> callbackAction) {
        super(BaseStageDto.allOff());
        controller.setItemsToView(userProgress, matchingDto, editable, callbackAction);
    }

    @Override
    public String getPathToMainStage() {
        return "task/matchingWindow/main";
    }
}
