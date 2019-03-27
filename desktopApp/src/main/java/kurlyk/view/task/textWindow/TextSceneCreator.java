package kurlyk.view.task.textWindow;


import kurlyk.transfer.QuestionDto;
import kurlyk.transfer.tasks.TextDto;
import kurlyk.view.common.stage.BaseStage;

public class TextSceneCreator extends BaseStage<TextController> {

    public TextSceneCreator(QuestionDto questionDto, TextDto textDto, boolean editable) {
        super();
        controller.setQuestion(questionDto, textDto, editable);
    }

    @Override
    public String getPathToMainStage() {
        return "task/textWindow/main";
    }
}
