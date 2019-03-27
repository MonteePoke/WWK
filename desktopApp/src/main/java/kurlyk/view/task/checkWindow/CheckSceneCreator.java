package kurlyk.view.task.checkWindow;


import kurlyk.transfer.QuestionDto;
import kurlyk.transfer.tasks.SelectDto;
import kurlyk.view.common.stage.BaseSceneCreator;

public class CheckSceneCreator extends BaseSceneCreator<CheckController> {

    public CheckSceneCreator(QuestionDto questionDto, SelectDto selectDto, boolean editable) {
        super();
        controller.setQuestion(questionDto, selectDto, editable);
    }

    @Override
    public String getPathToMainStage() {
        return "task/checkWindow/main";
    }
}
