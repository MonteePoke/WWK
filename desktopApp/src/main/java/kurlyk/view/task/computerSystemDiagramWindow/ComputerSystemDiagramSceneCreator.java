package kurlyk.view.task.computerSystemDiagramWindow;

import kurlyk.models.Question;
import kurlyk.models.UserProgress;
import kurlyk.transfer.tasks.ComputerSystemDto;
import kurlyk.view.common.dto.BaseStageDto;
import kurlyk.view.common.stage.base.BaseStage;

import java.util.function.Consumer;

public class ComputerSystemDiagramSceneCreator extends BaseStage<ComputerSystemDiagramController> {


    public ComputerSystemDiagramSceneCreator(UserProgress userProgress, ComputerSystemDto computerSystemDto, boolean editable, Consumer<Question> callbackAction) {
        super(BaseStageDto.allOff());
        controller.setQuestion(userProgress, computerSystemDto, editable, callbackAction);
    }

    @Override
    public String getPathToMainStage() {
        return "task/computerSystemDiagramWindow/main";
    }
}
