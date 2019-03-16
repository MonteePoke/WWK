package kurlyk.view.common.stage;

import javafx.stage.Stage;
import kurlyk.view.common.Properties;
import kurlyk.view.common.controller.Controller;
import kurlyk.view.common.dto.LoadDto;

public abstract class BaseStage <T extends Controller> extends Stage {

    protected T controller;

    public BaseStage() {
        super();
        LoadDto<T> loadDto = Loader.load(getPathToMainStage());
        controller = loadDto.getController();
        setScene(loadDto.getScene());
        setTitle(Properties.getInstance().getProperty("mainTitle"));
    }

    public abstract String getPathToMainStage();
}
