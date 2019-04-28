package kurlyk.view.create.createQuestionWindow;

import kurlyk.view.common.stage.base.BaseSceneCreator;

public class CreateQuestionSceneCreator extends BaseSceneCreator<CreateQuestionController> {


    public CreateQuestionSceneCreator() {
        super();
    }

    @Override
    public String getPathToMainStage() {
        return "create/createQuestionWindow/main";
    }
}
