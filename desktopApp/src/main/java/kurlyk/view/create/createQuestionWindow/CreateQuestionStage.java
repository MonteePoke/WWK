package kurlyk.view.create.createQuestionWindow;

import kurlyk.model.Question;
import kurlyk.view.common.dto.BaseStageDto;
import kurlyk.view.common.stage.base.BaseStage;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class CreateQuestionStage extends BaseStage<CreateQuestionController> {

    public CreateQuestionStage(
            Consumer<Question> callbackActionBefore,
            Consumer<Question> callbackActionAfter,
            Supplier<Question> createQuestionAction
    ) {
        super(BaseStageDto.allOff());
        controller.setCallbackActionBefore(callbackActionBefore);
        controller.setCallbackActionAfter(callbackActionAfter);
        controller.setQuestionCreator(createQuestionAction);
    }

    @Override
    public String getPathToMainStage() {
        return "create/createQuestionWindow/main";
    }
}
