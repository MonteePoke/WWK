package kurlyk.view.common.stage;

import javafx.scene.Scene;
import kurlyk.view.common.controller.Controller;
import kurlyk.view.common.dto.LoadDto;

public abstract class BaseSceneCreator<T extends Controller> implements SceneCreator {

    protected T controller;
    protected Scene scene;

    public BaseSceneCreator() {
        LoadDto<T> loadDto = Loader.load(getPathToMainStage());
        controller = loadDto.getController();
        scene = loadDto.getScene();
    }

    @Override
    public Scene getScene() {
        return scene;
    }

    public abstract String getPathToMainStage();
}
