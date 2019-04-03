package kurlyk.view.task.checkWindow;


import kurlyk.models.UserProgress;
import kurlyk.transfer.tasks.SelectDto;
import kurlyk.view.common.stage.BaseSceneCreator;

public class CheckSceneCreator extends BaseSceneCreator<CheckController> {

    public CheckSceneCreator(UserProgress userProgress, SelectDto selectDto, boolean editable) {
        super();
        controller.setQuestion(userProgress, selectDto, editable);
    }

    @Override
    public String getPathToMainStage() {
        return "task/checkWindow/main";
    }
}
