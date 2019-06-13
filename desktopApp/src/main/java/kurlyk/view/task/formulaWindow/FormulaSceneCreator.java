package kurlyk.view.task.formulaWindow;

import kurlyk.model.Question;
import kurlyk.view.common.dto.BaseStageDto;
import kurlyk.view.common.stage.base.BaseStage;

import java.util.function.Consumer;

public class FormulaSceneCreator extends BaseStage<FormulaController> {


    public FormulaSceneCreator(Question question, boolean editable, Consumer<Long> callbackAction) {
        super(BaseStageDto.allOff());
        controller.setQuestion(question, editable, callbackAction);
    }


    @Override
    public String getPathToMainStage() {
        return "task/formulaWindow/main";
    }
}
