package kurlyk.view.task.numberWindow;


import kurlyk.models.Question;
import kurlyk.transfer.tasks.NumberDto;
import kurlyk.view.common.stage.BaseStage;

public class NumberStage extends BaseStage<NumberController> {

    public NumberStage(Question question, NumberDto numberDto, boolean editable) {
        super();
//        controller.setQuestion(question, numberDto, editable);
    }

    @Override
    public String getPathToMainStage() {
        return "task/numberWindow/main";
    }
}
