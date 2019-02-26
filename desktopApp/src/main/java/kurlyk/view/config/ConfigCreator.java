package kurlyk.view.config;

import javafx.stage.Stage;
import kurlyk.view.fxCommon.StageEnum;
import kurlyk.view.fxCommon.StagePool;
import kurlyk.view.fxCommon.Initializer;

public class ConfigCreator {

    private static final String NAME_TITLE = "VVDram";
    public static final double MIN_WIDTH = 1000d;
    public static final double MIN_HEIGHT = 600d;

    public static void setSceneRoot(StageEnum stageEnum){
        Stage stage = StagePool.getInstance().getStage(stageEnum);
        Initializer initializer = new ConfigInitializer().initialize();

        stage.setTitle(NAME_TITLE);
        stage.setMinWidth(MIN_WIDTH);
        stage.setMinHeight(MIN_HEIGHT);
        stage.centerOnScreen();
        stage.setScene(initializer.getRoot().getScene());
    }
}
