package kurlyk.view.create.createQuestionWindow;

import kurlyk.models.Question;
import kurlyk.view.common.stage.base.BaseStage;

import java.util.function.Consumer;

public class CreateQuestionStage extends BaseStage<CreateQuestionController> {


    public CreateQuestionStage() {
        super();
    }

    public CreateQuestionStage(Consumer<Question> setQuestionInTableAction) {
        super();
        controller.setQuestionConsumer(setQuestionInTableAction);
    }

    @Override
    public String getPathToMainStage() {
        return "create/createQuestionWindow/main";
    }
}
