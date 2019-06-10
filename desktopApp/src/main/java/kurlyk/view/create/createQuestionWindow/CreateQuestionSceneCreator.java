package kurlyk.view.create.createQuestionWindow;

import kurlyk.models.Question;
import kurlyk.view.common.dto.BaseStageDto;
import kurlyk.view.common.stage.base.BaseSceneCreator;

import java.util.function.Consumer;

public class CreateQuestionSceneCreator extends BaseSceneCreator<CreateQuestionController> {


    public CreateQuestionSceneCreator() {
        super(BaseStageDto.allOff());
    }

    public CreateQuestionSceneCreator(Consumer<Question> setQuestionInTableAction) {
        super();
        controller.setQuestionConsumer(setQuestionInTableAction);
    }

    @Override
    public String getPathToMainStage() {
        return "create/createQuestionWindow/main";
    }
}
