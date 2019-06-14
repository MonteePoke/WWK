package kurlyk.view.create.createQuestionWindow;

import kurlyk.model.Question;
import kurlyk.view.common.dto.BaseStageDto;
import kurlyk.view.common.stage.base.BaseSceneCreator;

import java.util.function.Consumer;

public class CreateQuestionSceneCreator extends BaseSceneCreator<CreateQuestionController> {


    public CreateQuestionSceneCreator() {
        this(question -> {}, question -> {});
    }

    public CreateQuestionSceneCreator(Consumer<Question> callbackActionBefore, Consumer<Question> callbackActionAfter) {
        super(BaseStageDto.allOff());
        controller.setCallbackActionBefore(callbackActionBefore);
        controller.setCallbackActionAfter(callbackActionAfter);
    }

    @Override
    public String getPathToMainStage() {
        return "create/createQuestionWindow/main";
    }
}
