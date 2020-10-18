package kurlyk.view.task.numberWindow;


import kurlyk.model.Question;
import kurlyk.view.common.dto.BaseStageDto;
import kurlyk.view.common.stage.Stages;
import kurlyk.view.common.stage.base.BaseStage;
import kurlyk.view.task.TabPurpose;

import java.util.function.Consumer;

public class NumberSceneCreator extends BaseStage<NumberController> {

    public NumberSceneCreator(Question question, boolean editable, Consumer<Question> callbackActionBefore, Consumer<Question> callbackActionAfter, Stages stageForClose, TabPurpose tabPurpose) {
        super(BaseStageDto.allOff());
        controller.setQuestion(question, editable, callbackActionBefore, callbackActionAfter, stageForClose, tabPurpose);
    }

    @Override
    public String getPathToMainStage() {
        return "task/numberWindow/main";
    }
}
