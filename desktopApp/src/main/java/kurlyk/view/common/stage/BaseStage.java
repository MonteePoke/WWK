package kurlyk.view.common.stage;

import javafx.stage.Stage;
import kurlyk.view.common.Loader;
import kurlyk.view.common.Properties;
import kurlyk.view.common.dto.LoadInfo;

public abstract class BaseStage extends Stage {

    public BaseStage() {
        super();
        LoadInfo loadInfo = Loader.load(getPathToMainStage());
        setScene(loadInfo.getScene());
        setTitle(Properties.getInstance().getProperty("mainTitle"));
    }

    public abstract String getPathToMainStage();
}
