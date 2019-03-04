package kurlyk.view.fxCommon;

import javafx.stage.Stage;
import kurlyk.view.Properties;

public abstract class BaseStage extends Stage {

    public BaseStage() {
        setScene(FxmlLoader.loadScene(getPathToMainStage()));
        setTitle(Properties.getInstance().getProperty("mainTitle"));
    }

    public abstract String getPathToMainStage();
}
