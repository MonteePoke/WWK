package kurlyk.view.task.formulaWindow;

import kurlyk.transfer.QuestionDto;
import kurlyk.transfer.tasks.FormulaDto;
import kurlyk.view.common.stage.BaseStage;

public class FormulaSceneCreator extends BaseStage<FormulaController> {


    public FormulaSceneCreator(QuestionDto questionDto, FormulaDto formulaDto, boolean editable) {
        super();
        controller.setQuestion(questionDto, formulaDto, editable);
    }


    @Override
    public String getPathToMainStage() {
        return "task/formulaWindow/main";
    }
}
