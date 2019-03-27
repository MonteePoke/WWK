package kurlyk.view.performLabWindow;

import kurlyk.transfer.QuestionDto;
import kurlyk.view.common.stage.BaseStage;

import java.util.List;

public class PerformLabStage extends BaseStage<PerformLabController> {


    public PerformLabStage(List<QuestionDto> questionDtos) {
        super();
        controller.createQuestion(questionDtos);
    }

    @Override
    public String getPathToMainStage() {
        return "performLabWindow/main";
    }
}
