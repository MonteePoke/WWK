package kurlyk.view.create.createQuestionWindow;

import kurlyk.view.common.dto.BaseStageDto;
import kurlyk.view.common.stage.base.BaseStage;

import java.util.function.Consumer;

public class CreateQuestionStage extends BaseStage<CreateQuestionController> {


    public CreateQuestionStage() {
        this(null);
    }

    public CreateQuestionStage(Consumer<Long> setQuestionInTableAction) {
        super(BaseStageDto.allOff());
        controller.setQuestionConsumer(setQuestionInTableAction);
    }

    @Override
    public String getPathToMainStage() {
        return "create/createQuestionWindow/main";
    }
}
