package kurlyk.view.task.computerSystemDiagramWindow;

import kurlyk.models.UserProgress;
import kurlyk.transfer.tasks.ComputerSystemDto;
import kurlyk.view.common.dto.BaseStageDto;
import kurlyk.view.common.stage.base.BaseStage;

public class ComputerSystemDiagramSceneCreator extends BaseStage<ComputerSystemDiagramController> {


    public ComputerSystemDiagramSceneCreator(UserProgress userProgress, ComputerSystemDto computerSystemDto, boolean editable) {
        super(BaseStageDto.allOff());
        controller.setQuestion(userProgress, computerSystemDto, editable);
    }

    @Override
    public String getPathToMainStage() {
        return "task/computerSystemDiagramWindow/main";
    }
}
