package kurlyk.view.task.textWindow;


import kurlyk.transfer.QuestionDto;
import kurlyk.transfer.tasks.TextDto;
import kurlyk.view.common.stage.BaseStage;

public class TextStage extends BaseStage<TextController> {

    public TextStage(QuestionDto questionDto, TextDto textDto, boolean editable) {
        super();
        controller.setQuestion(questionDto, textDto, editable);
    }

    @Override
    public String getPathToMainStage() {
        return "task/textWindow/main";
    }
}
