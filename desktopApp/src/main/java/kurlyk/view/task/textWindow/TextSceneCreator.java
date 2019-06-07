package kurlyk.view.task.textWindow;


import kurlyk.models.Question;
import kurlyk.view.common.dto.BaseStageDto;
import kurlyk.view.common.stage.base.BaseStage;

import java.util.function.Consumer;

public class TextSceneCreator extends BaseStage<TextController> {

    public TextSceneCreator(Question question, boolean editable, Consumer<Question> callbackAction) {
        super(BaseStageDto.allOff());
        controller.setQuestion(question, editable, callbackAction);
    }

    @Override
    public String getPathToMainStage() {
        return "task/textWindow/main";
    }
}
