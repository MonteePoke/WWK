package kurlyk.view.task.numberWindow;


import kurlyk.models.Question;
import kurlyk.view.common.dto.BaseStageDto;
import kurlyk.view.common.stage.base.BaseStage;

import java.util.function.Consumer;

public class NumberSceneCreator extends BaseStage<NumberController> {

    public NumberSceneCreator(Question question, boolean editable, Consumer<Question> callbackAction) {
        super(BaseStageDto.allOff());
        controller.setQuestion(question, editable, callbackAction);
    }

    @Override
    public String getPathToMainStage() {
        return "task/numberWindow/main";
    }
}
