package kurlyk.view.create.questionListWindow;

import kurlyk.models.Question;
import kurlyk.view.common.dto.BaseStageDto;
import kurlyk.view.common.stage.base.BaseStage;

import java.util.function.Consumer;

public class QuestionListStage extends BaseStage<QuestionListController> {

    public QuestionListStage(Consumer<Question> applySelection) {
        this(applySelection, false);
    }

    public QuestionListStage(Consumer<Question> applySelection, boolean isBaseStage) {
        super(BaseStageDto.allOff());
        controller.setOk(applySelection, isBaseStage);
    }

    @Override
    public String getPathToMainStage() {
        return "create/questionListWindow/main";
    }
}
