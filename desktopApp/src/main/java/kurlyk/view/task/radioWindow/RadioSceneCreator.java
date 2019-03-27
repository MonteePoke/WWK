package kurlyk.view.task.radioWindow;

import kurlyk.transfer.QuestionDto;
import kurlyk.transfer.tasks.SelectDto;
import kurlyk.view.common.stage.BaseSceneCreator;

public class RadioSceneCreator extends BaseSceneCreator<RadioController> {


    public RadioSceneCreator(QuestionDto questionDto, SelectDto selectDto, boolean editable) {
        super();
        controller.setQuestion(questionDto, selectDto, editable);
    }

    @Override
    public String getPathToMainStage() {
        return "task/radioWindow/main";
    }
}
