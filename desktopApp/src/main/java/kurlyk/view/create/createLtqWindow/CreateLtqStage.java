package kurlyk.view.create.createLtqWindow;

import kurlyk.WorkEntityType;
import kurlyk.view.common.stage.base.BaseStage;

public class CreateLtqStage extends BaseStage<CreateLtqController> {


    public CreateLtqStage(WorkEntityType type) {
        super();
        switch (type){
            case LAB_WORK:
                controller.createLabWork();
                break;
            case TASK:
                controller.createTask();
                break;
            case QUESTION:
                controller.createQuestion();
                break;
        }
    }

    @Override
    public String getPathToMainStage() {
        return "create/createLtqWindow/main";
    }
}
