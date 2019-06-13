package kurlyk.view.task.computerSystemDiagramWindow;

import kurlyk.model.Question;
import kurlyk.view.common.dto.BaseStageDto;
import kurlyk.view.common.stage.base.BaseStage;

import java.util.function.Consumer;

public class ComputerSystemDiagramSceneCreator extends BaseStage<ComputerSystemDiagramController> {


    public ComputerSystemDiagramSceneCreator(Question question, boolean editable, Consumer<Long> callbackAction) {
        super(BaseStageDto.allOff());
        controller.setQuestion(question, editable, callbackAction);
    }

    @Override
    public String getPathToMainStage() {
        return "task/computerSystemDiagramWindow/main";
    }
}
