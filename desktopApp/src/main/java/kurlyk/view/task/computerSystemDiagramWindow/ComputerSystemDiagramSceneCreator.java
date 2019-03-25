package kurlyk.view.task.computerSystemDiagramWindow;

import kurlyk.transfer.TaskDto;
import kurlyk.transfer.tasks.ComputerSystemDto;
import kurlyk.view.common.stage.BaseStage;

public class ComputerSystemDiagramSceneCreator extends BaseStage<ComputerSystemDiagramController> {


    public ComputerSystemDiagramSceneCreator(TaskDto taskDto, ComputerSystemDto computerSystemDto, boolean editable) {
        super();
        controller.setQuestion(taskDto, computerSystemDto, editable);
    }

    @Override
    public String getPathToMainStage() {
        return "task/computerSystemDiagramWindow/main";
    }
}
