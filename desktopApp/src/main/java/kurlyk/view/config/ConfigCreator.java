package kurlyk.view.config;

import javafx.stage.Stage;
import kurlyk.view.StageEnum;
import kurlyk.view.StagePool;
import kurlyk.view.fxCommon.IInitializer;

public class ConfigCreator {

    private static final String NAME_TITLE = "VVDram";
    public static final double MIN_WIDTH = 1000d;
    public static final double MIN_HEIGHT = 600d;

    public static void setSceneRoot(StageEnum stageEnum){
        Stage stage = StagePool.getInstance().getStage(stageEnum);
        IInitializer initializer = new ConfigInitializer().initialize();

        stage.setTitle(NAME_TITLE);
        stage.setMinWidth(MIN_WIDTH);
        stage.setMinHeight(MIN_HEIGHT);
        stage.centerOnScreen();
        stage.getScene().setRoot(initializer.getRoot());
    }
}
