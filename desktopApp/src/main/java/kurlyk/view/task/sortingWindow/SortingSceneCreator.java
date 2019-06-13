package kurlyk.view.task.sortingWindow;


import kurlyk.model.Question;
import kurlyk.view.common.dto.BaseStageDto;
import kurlyk.view.common.stage.base.BaseSceneCreator;

import java.util.function.Consumer;

public class SortingSceneCreator extends BaseSceneCreator<SortingController> {

    public SortingSceneCreator(Question question, boolean editable, Consumer<Long> callbackAction) {
        super(BaseStageDto.allOff());
        controller.setItemsToView(question, editable, callbackAction);
    }

    @Override
    public String getPathToMainStage() {
        return "task/sortingWindow/main";
    }
}
