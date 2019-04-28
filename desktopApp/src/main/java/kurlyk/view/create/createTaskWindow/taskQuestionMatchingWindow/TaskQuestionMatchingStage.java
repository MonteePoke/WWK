package kurlyk.view.create.createTaskWindow.taskQuestionMatchingWindow;

import kurlyk.models.Task;
import kurlyk.view.common.stage.base.BaseStage;

public class TaskQuestionMatchingStage extends BaseStage<TaskQuestionMatchingController> {


    public TaskQuestionMatchingStage(Task task) {
        super();
        controller.setContent(task);
    }

    @Override
    public String getPathToMainStage() {
        return "create/createTaskWindow/taskQuestionMatchingWindow/main";
    }
}
