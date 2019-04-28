package kurlyk.view.showAnswerWindow;

import kurlyk.models.Question;
import kurlyk.view.common.stage.base.BaseStage;

public class ShowAnswerStage extends BaseStage<ShowAnswerController> {


    public ShowAnswerStage(Question question) {
        super();
        controller.setQuestion(question);
    }

    @Override
    public String getPathToMainStage() {
        return "showAnswerWindow/main";
    }
}