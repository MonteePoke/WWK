package kurlyk.view.create.questionListWindow;

import kurlyk.model.Question;
import kurlyk.view.common.dto.BaseStageDto;
import kurlyk.view.common.stage.base.BaseStage;

import java.util.function.Consumer;

public class QuestionListStage extends BaseStage<QuestionListController> {

    public QuestionListStage(Consumer<Question> applySelectionBefore, Consumer<Question> applySelectionAfter) {
        super(BaseStageDto.allOff());
        controller.setSelectActionBeforeSave(applySelectionBefore);
        controller.setSelectActionAfterSave(applySelectionAfter);
        controller.setCloseAction(this::close);
    }

    @Override
    public String getPathToMainStage() {
        return "create/questionListWindow/main";
    }
}
