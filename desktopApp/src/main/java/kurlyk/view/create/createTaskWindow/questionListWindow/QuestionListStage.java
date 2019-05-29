package kurlyk.view.create.createTaskWindow.questionListWindow;

import kurlyk.models.Question;
import kurlyk.view.common.dto.BaseStageDto;
import kurlyk.view.common.stage.base.BaseStage;

import java.util.function.Consumer;

public class QuestionListStage extends BaseStage<QuestionListController> {


    public QuestionListStage(Consumer<Question> applySelection) {
        super(BaseStageDto.allOff());
        controller.setOk(applySelection);
    }

    @Override
    public String getPathToMainStage() {
        return "create/createTaskWindow/questionListWindow/main";
    }
}
