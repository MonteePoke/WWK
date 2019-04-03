package kurlyk.view.task.textWindow;


import kurlyk.models.Question;
import kurlyk.transfer.tasks.TextDto;
import kurlyk.view.common.stage.BaseStage;

public class TextStage extends BaseStage<TextController> {

    public TextStage(Question question, TextDto textDto, boolean editable) {
        super();
//        controller.setQuestion(question, textDto, editable);
    }

    @Override
    public String getPathToMainStage() {
        return "task/textWindow/main";
    }
}
