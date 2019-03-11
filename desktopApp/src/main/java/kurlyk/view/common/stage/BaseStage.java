package kurlyk.view.common.stage;

import javafx.stage.Stage;
import kurlyk.view.common.Properties;
import kurlyk.view.common.controller.Controller;
import kurlyk.view.common.dto.LoadInfo;
import lombok.Getter;

public abstract class BaseStage extends Stage {

    @Getter private Controller controller;

    public BaseStage() {
        super();
        LoadInfo loadInfo = Loader.load(getPathToMainStage());
        controller = loadInfo.getController();
        setScene(loadInfo.getScene());
        setTitle(Properties.getInstance().getProperty("mainTitle"));
    }

    public abstract String getPathToMainStage();
}
