package kurlyk.view.task.computerSystemDiagramWindow;

import kurlyk.transfer.QuestionDto;
import kurlyk.transfer.tasks.ComputerSystemDto;
import kurlyk.view.common.stage.BaseStage;

public class ComputerSystemDiagramSceneCreator extends BaseStage<ComputerSystemDiagramController> {


    public ComputerSystemDiagramSceneCreator(QuestionDto questionDto, ComputerSystemDto computerSystemDto, boolean editable) {
        super();
        controller.setQuestion(questionDto, computerSystemDto, editable);
    }

    @Override
    public String getPathToMainStage() {
        return "task/computerSystemDiagramWindow/main";
    }
}
