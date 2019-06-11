package kurlyk.view.create.createQuestionWindow;

import kurlyk.view.common.dto.BaseStageDto;
import kurlyk.view.common.stage.base.BaseSceneCreator;

import java.util.function.Consumer;

public class CreateQuestionSceneCreator extends BaseSceneCreator<CreateQuestionController> {


    public CreateQuestionSceneCreator() {
        this(null);
    }

    public CreateQuestionSceneCreator(Consumer<Long> setQuestionInTableAction) {
        super(BaseStageDto.allOff());
        controller.setQuestionConsumer(setQuestionInTableAction);
    }

    @Override
    public String getPathToMainStage() {
        return "create/createQuestionWindow/main";
    }
}
