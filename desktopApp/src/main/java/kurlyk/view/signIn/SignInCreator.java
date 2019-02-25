package kurlyk.view.signIn;

import javafx.stage.Stage;
import kurlyk.view.StageEnum;
import kurlyk.view.StagePool;
import kurlyk.view.fxCommon.Initializer;

public class SignInCreator {

    public static void setSceneRoot(StageEnum stageEnum){
        Stage stage = StagePool.getInstance().getStage(stageEnum);
        Initializer initializer = new SignInInitializer().initialize();

        stage.centerOnScreen();
        stage.setScene(initializer.getRoot().getScene());
    }
}
