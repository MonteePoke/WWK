package kurlyk.view.common.stage.base;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import kurlyk.view.common.controller.Controller;
import kurlyk.view.common.dto.BaseStageDto;
import kurlyk.view.common.dto.LoadDto;
import kurlyk.view.common.stage.Loader;
import kurlyk.view.common.stage.SceneCreator;

public abstract class BaseSceneCreator<T extends Controller> implements SceneCreator {

    protected T controller;
    protected Scene scene;
    protected BorderPane root;

    public BaseSceneCreator() {
        this(BaseStageDto.allInclusive());
    }

    public BaseSceneCreator(BaseStageDto baseStageDto) {
        LoadDto<T> loadDto = Loader.load(getPathToMainStage());
        controller = loadDto.getController();
        if (baseStageDto.isNeedMenu() || baseStageDto.isNeedTree()) {
            scene = createSceneBoxTemplate(loadDto.getScene());
        } else {
            scene = loadDto.getScene();
        }
        if (baseStageDto.isNeedMenu()) {
            BaseStage.setMenuToScene(root);
        }
        if (baseStageDto.isNeedTree()) {
            BaseStage.setTreeViewToScene(root);
        }
    }

    private Scene createSceneBoxTemplate(Scene scene){
        root = new BorderPane();
        root.setCenter(scene.getRoot());
        scene.setRoot(root);
        return scene;
    }

    @Override
    public Scene getScene() {
        return scene;
    }

    public abstract String getPathToMainStage();
}
