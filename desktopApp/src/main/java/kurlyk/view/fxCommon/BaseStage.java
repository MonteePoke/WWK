package kurlyk.view.fxCommon;

import javafx.stage.Stage;
import kurlyk.common.PropertyReader;

public abstract class BaseStage extends Stage {

    public BaseStage() {
        setScene(FxmlLoader.loadScene(getPathToMainStage()));
        setTitle(PropertyReader.getInstance().getProperty("mainTitle"));
    }

    public abstract String getPathToMainStage();
}
