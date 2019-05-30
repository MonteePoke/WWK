package kurlyk.view.task.formulaWindow;

import kurlyk.models.Question;
import kurlyk.models.UserProgress;
import kurlyk.transfer.tasks.FormulaDto;
import kurlyk.view.common.dto.BaseStageDto;
import kurlyk.view.common.stage.base.BaseStage;

import java.util.function.Consumer;

public class FormulaSceneCreator extends BaseStage<FormulaController> {


    public FormulaSceneCreator(UserProgress userProgress, FormulaDto formulaDto, boolean editable, Consumer<Question> callbackAction) {
        super(BaseStageDto.allOff());
        controller.setQuestion(userProgress, formulaDto, editable, callbackAction);
    }


    @Override
    public String getPathToMainStage() {
        return "task/formulaWindow/main";
    }
}
