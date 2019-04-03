package kurlyk.view.task.computerSystemDiagramWindow;

import kurlyk.models.Question;
import kurlyk.transfer.tasks.ComputerSystemDto;
import kurlyk.view.common.stage.BaseStage;

public class ComputerSystemDiagramStage extends BaseStage<ComputerSystemDiagramController> {


    public ComputerSystemDiagramStage(Question question, ComputerSystemDto computerSystemDto, boolean editable) {
        super();
//        controller.setQuestion(question, computerSystemDto, editable);
    }

    @Override
    public String getPathToMainStage() {
        return "task/computerSystemDiagramWindow/main";
    }
}
