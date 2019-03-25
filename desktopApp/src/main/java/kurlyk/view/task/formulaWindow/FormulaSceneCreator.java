package kurlyk.view.task.formulaWindow;

import kurlyk.transfer.TaskDto;
import kurlyk.transfer.tasks.FormulaDto;
import kurlyk.view.common.stage.BaseStage;

public class FormulaSceneCreator extends BaseStage<FormulaController> {


    public FormulaSceneCreator(TaskDto taskDto, FormulaDto formulaDto, boolean editable) {
        super();
        controller.setQuestion(taskDto, formulaDto, editable);
    }


    @Override
    public String getPathToMainStage() {
        return "task/formulaWindow/main";
    }
}
