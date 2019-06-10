package kurlyk.view.create.questionListWindow;

import kurlyk.transfer.QuestionForTableDto;
import kurlyk.view.common.dto.BaseStageDto;
import kurlyk.view.common.stage.base.BaseStage;

import java.util.function.Consumer;

public class QuestionListStage extends BaseStage<QuestionListController> {

    public QuestionListStage(Consumer<QuestionForTableDto> applySelection) {
        super(BaseStageDto.allOff());
        controller.setSelectAction(applySelection);
        controller.setCloseAction(this::close);
    }

    @Override
    public String getPathToMainStage() {
        return "create/questionListWindow/main";
    }
}
