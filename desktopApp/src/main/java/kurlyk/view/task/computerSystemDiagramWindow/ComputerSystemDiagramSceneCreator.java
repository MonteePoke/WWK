package kurlyk.view.task.computerSystemDiagramWindow;

import kurlyk.model.Question;
import kurlyk.view.common.dto.BaseStageDto;
import kurlyk.view.common.stage.Stages;
import kurlyk.view.common.stage.base.BaseStage;

import java.util.function.Consumer;

public class ComputerSystemDiagramSceneCreator extends BaseStage<ComputerSystemDiagramController> {


    public ComputerSystemDiagramSceneCreator(Question question, boolean editable, Consumer<Question> callbackActionBefore, Consumer<Question> callbackActionAfter, Stages stageForClose) {
        super(BaseStageDto.allOff());
        controller.setQuestion(question, editable, callbackActionBefore, callbackActionAfter, stageForClose);
    }

    @Override
    public String getPathToMainStage() {
        return "task/computerSystemDiagramWindow/main";
    }
}
