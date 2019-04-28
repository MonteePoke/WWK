package kurlyk.view.task.formulaWindow;

import kurlyk.models.Question;
import kurlyk.transfer.tasks.FormulaDto;
import kurlyk.view.common.stage.base.BaseStage;

public class FormulaStage extends BaseStage<FormulaController> {


    public FormulaStage(Question question, FormulaDto formulaDto, boolean editable) {
        super();
//        controller.setQuestion(question, formulaDto, editable);
    }


    @Override
    public String getPathToMainStage() {
        return "task/formulaWindow/main";
    }
}
