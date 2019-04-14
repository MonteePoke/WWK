package kurlyk.view.create.createTaskWindow.questionListWindow;

import kurlyk.models.Question;
import kurlyk.view.common.stage.BaseStage;

import java.util.function.Consumer;

public class QuestionListStage extends BaseStage<QuestionListController> {


    public QuestionListStage(Consumer<Question> applySelection) {
        super();
        controller.setOk(applySelection);
    }

    @Override
    public String getPathToMainStage() {
        return "create/createTaskWindow/questionListWindow/main";
    }
}
