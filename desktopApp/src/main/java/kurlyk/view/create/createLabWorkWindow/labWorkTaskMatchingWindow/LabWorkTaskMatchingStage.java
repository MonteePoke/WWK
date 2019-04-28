package kurlyk.view.create.createLabWorkWindow.labWorkTaskMatchingWindow;

import kurlyk.models.LabWork;
import kurlyk.view.common.stage.base.BaseStage;

public class LabWorkTaskMatchingStage extends BaseStage<LabWorkTaskMatchingController> {


    public LabWorkTaskMatchingStage(LabWork labWork) {
        super();
        controller.setContent(labWork);
    }

    @Override
    public String getPathToMainStage() {
        return "create/createLabWorkWindow/labWorkTaskMatchingWindow/main";
    }
}
