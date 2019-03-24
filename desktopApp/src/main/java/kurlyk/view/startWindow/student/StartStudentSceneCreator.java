package kurlyk.view.startWindow.student;

import kurlyk.view.common.stage.BaseSceneCreator;

public class StartStudentSceneCreator extends BaseSceneCreator<StartStudentController> {


    public StartStudentSceneCreator() {
        super();
    }

    @Override
    public String getPathToMainStage() {
        return "startWindow/student/main";
    }
}
