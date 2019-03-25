package kurlyk.view.task.textWindow;


import kurlyk.transfer.TaskDto;
import kurlyk.transfer.tasks.TextDto;
import kurlyk.view.common.stage.BaseStage;

public class TextSceneCreator extends BaseStage<TextController> {

    public TextSceneCreator(TaskDto taskDto, TextDto textDto, boolean editable) {
        super();
        controller.setQuestion(taskDto, textDto, editable);
    }

    @Override
    public String getPathToMainStage() {
        return "task/textWindow/main";
    }
}
