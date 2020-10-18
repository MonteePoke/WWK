package kurlyk.view.task.radioWindow;

import kurlyk.model.Question;
import kurlyk.view.common.dto.BaseStageDto;
import kurlyk.view.common.stage.Stages;
import kurlyk.view.common.stage.base.BaseSceneCreator;
import kurlyk.view.task.TabPurpose;

import java.util.function.Consumer;

public class RadioSceneCreator extends BaseSceneCreator<RadioController> {


    public RadioSceneCreator(Question question, boolean editable, Consumer<Question> callbackActionBefore, Consumer<Question> callbackActionAfter, Stages stageForClose, TabPurpose tabPurpose) {
        super(BaseStageDto.allOff());
        controller.setQuestion(question, editable, callbackActionBefore, callbackActionAfter, stageForClose, tabPurpose);
    }

    @Override
    public String getPathToMainStage() {
        return "task/radioWindow/main";
    }
}
