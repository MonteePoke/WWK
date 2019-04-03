package kurlyk.view.task.textWindow;


import kurlyk.models.UserProgress;
import kurlyk.transfer.tasks.TextDto;
import kurlyk.view.common.stage.BaseStage;

public class TextSceneCreator extends BaseStage<TextController> {

    public TextSceneCreator(UserProgress userProgress, TextDto textDto, boolean editable) {
        super();
        controller.setQuestion(userProgress, textDto, editable);
    }

    @Override
    public String getPathToMainStage() {
        return "task/textWindow/main";
    }
}
