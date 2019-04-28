package kurlyk.view.task.formulaWindow;

import kurlyk.models.UserProgress;
import kurlyk.transfer.tasks.FormulaDto;
import kurlyk.view.common.stage.base.BaseStage;

public class FormulaSceneCreator extends BaseStage<FormulaController> {


    public FormulaSceneCreator(UserProgress userProgress, FormulaDto formulaDto, boolean editable) {
        super();
        controller.setQuestion(userProgress, formulaDto, editable);
    }


    @Override
    public String getPathToMainStage() {
        return "task/formulaWindow/main";
    }
}
