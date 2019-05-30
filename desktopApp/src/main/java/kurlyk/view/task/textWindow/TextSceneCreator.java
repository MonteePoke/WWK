package kurlyk.view.task.textWindow;


import kurlyk.models.Question;
import kurlyk.models.UserProgress;
import kurlyk.transfer.tasks.TextDto;
import kurlyk.view.common.dto.BaseStageDto;
import kurlyk.view.common.stage.base.BaseStage;

import java.util.function.Consumer;

public class TextSceneCreator extends BaseStage<TextController> {

    public TextSceneCreator(UserProgress userProgress, TextDto textDto, boolean editable, Consumer<Question> callbackAction) {
        super(BaseStageDto.allOff());
        controller.setQuestion(userProgress, textDto, editable, callbackAction);
    }

    @Override
    public String getPathToMainStage() {
        return "task/textWindow/main";
    }
}
