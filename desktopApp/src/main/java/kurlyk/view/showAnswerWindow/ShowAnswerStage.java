package kurlyk.view.showAnswerWindow;

import kurlyk.view.common.dto.BaseStageDto;
import kurlyk.view.common.stage.base.BaseStage;

public class ShowAnswerStage extends BaseStage<ShowAnswerController> {


    public ShowAnswerStage(Long questionId) {
        super(BaseStageDto.allOff());
        controller.setQuestion(questionId);
    }

    @Override
    public String getPathToMainStage() {
        return "showAnswerWindow/main";
    }
}
