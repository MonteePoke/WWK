package kurlyk.view.common.stage;

import javafx.stage.Stage;
import kurlyk.view.common.FxmlLoader;
import kurlyk.view.common.Properties;

public abstract class BaseStage extends Stage {

    public BaseStage() {
        setScene(FxmlLoader.loadScene(getPathToMainStage()));
        setTitle(Properties.getInstance().getProperty("mainTitle"));
    }

    public abstract String getPathToMainStage();
}
