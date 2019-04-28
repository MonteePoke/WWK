package kurlyk.view.task.numberWindow;


import kurlyk.models.UserProgress;
import kurlyk.transfer.tasks.NumberDto;
import kurlyk.view.common.stage.base.BaseStage;

public class NumberSceneCreator extends BaseStage<NumberController> {

    public NumberSceneCreator(UserProgress userProgress, NumberDto numberDto, boolean editable) {
        super();
        controller.setQuestion(userProgress, numberDto, editable);
    }

    @Override
    public String getPathToMainStage() {
        return "task/numberWindow/main";
    }
}
