package kurlyk.view.signIn;

import javafx.stage.Stage;
import kurlyk.view.fxCommon.StageEnum;
import kurlyk.view.fxCommon.StagePool;
import kurlyk.view.fxCommon.Initializer;

public class SignInCreator {

    public static void setSceneRoot(StageEnum stageEnum){
        Stage stage = StagePool.getInstance().getStage(stageEnum);
        Initializer initializer = new SignInInitializer().initialize();

        stage.centerOnScreen();
        stage.setScene(initializer.getRoot().getScene());
    }
}
