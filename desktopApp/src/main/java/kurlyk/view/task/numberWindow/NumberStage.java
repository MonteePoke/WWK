package kurlyk.view.task.numberWindow;


import kurlyk.transfer.QuestionDto;
import kurlyk.transfer.tasks.NumberDto;
import kurlyk.view.common.stage.BaseStage;

public class NumberStage extends BaseStage<NumberController> {

    public NumberStage(QuestionDto questionDto, NumberDto numberDto, boolean editable) {
        super();
        controller.setQuestion(questionDto, numberDto, editable);
    }

    @Override
    public String getPathToMainStage() {
        return "task/numberWindow/main";
    }
}
