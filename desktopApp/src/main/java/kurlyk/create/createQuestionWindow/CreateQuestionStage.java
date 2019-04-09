package kurlyk.create.createQuestionWindow;

import kurlyk.view.common.stage.BaseStage;

public class CreateQuestionStage extends BaseStage<CreateQuestionController> {


    public CreateQuestionStage() {
        super();
    }

    @Override
    public String getPathToMainStage() {
        return "create/createQuestionWindow/main";
    }
}
