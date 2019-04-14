package kurlyk.view.create.createLabWorkWindow.taskListWindow;

import kurlyk.models.Task;
import kurlyk.view.common.stage.BaseStage;

import java.util.function.Consumer;

public class TaskListStage extends BaseStage<TaskListController> {


    public TaskListStage(Consumer<Task> applySelection) {
        super();
        controller.setOk(applySelection);
    }

    @Override
    public String getPathToMainStage() {
        return "create/createLabWorkWindow/taskListWindow/main";
    }
}
