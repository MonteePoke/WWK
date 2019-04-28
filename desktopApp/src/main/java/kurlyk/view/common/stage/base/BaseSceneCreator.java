package kurlyk.view.common.stage.base;

import javafx.scene.Scene;
import kurlyk.view.common.controller.Controller;
import kurlyk.view.common.dto.LoadDto;
import kurlyk.view.common.stage.Loader;
import kurlyk.view.common.stage.SceneCreator;

public abstract class BaseSceneCreator<T extends Controller> implements SceneCreator {

    protected T controller;
    protected Scene scene;

    public BaseSceneCreator(boolean needMenu) {
        LoadDto<T> loadDto = Loader.load(getPathToMainStage());
        controller = loadDto.getController();
        if (needMenu) {
            BaseStage.setMenuToScene(loadDto.getScene());
        }
        scene = loadDto.getScene();
    }

    public BaseSceneCreator() {
        this(true);
    }

    @Override
    public Scene getScene() {
        return scene;
    }

    public abstract String getPathToMainStage();
}
