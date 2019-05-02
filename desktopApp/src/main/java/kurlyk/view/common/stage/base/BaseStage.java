package kurlyk.view.common.stage.base;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import kurlyk.view.common.ViewProperties;
import kurlyk.view.common.controller.Controller;
import kurlyk.view.common.dto.LoadDto;
import kurlyk.view.common.stage.Loader;
import kurlyk.view.components.menu.MainMenu;
import kurlyk.view.components.toolbar.ToolbarSceneCreator;

public abstract class BaseStage <T extends Controller> extends Stage {

    protected T controller;

    public BaseStage(boolean needMenu) {
        super();
        LoadDto<T> loadDto = Loader.load(getPathToMainStage());
        controller = loadDto.getController();
        if (needMenu) {
            setMenuToScene(loadDto.getScene());
        }
        setScene(loadDto.getScene());
        setTitle(ViewProperties.getInstance().getProperty("mainTitle"));
        setMinWidth(600);
        setMinHeight(400);
    }

    public BaseStage() {
        this(true);
    }

    public static void setMenuToScene(Scene scene){
        BorderPane root = new BorderPane();
        root.setTop(new VBox(
                new MainMenu(),
                new ToolbarSceneCreator().getScene().getRoot()
        ));
        root.setCenter(scene.getRoot());
        scene.setRoot(root);
    }

    public abstract String getPathToMainStage();
}
