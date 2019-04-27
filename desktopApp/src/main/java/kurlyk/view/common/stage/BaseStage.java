package kurlyk.view.common.stage;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import kurlyk.view.common.ViewProperties;
import kurlyk.view.common.component.menu.MainMenu;
import kurlyk.view.common.controller.Controller;
import kurlyk.view.common.dto.LoadDto;

public abstract class BaseStage <T extends Controller> extends Stage {

    protected T controller;

    public BaseStage() {
        super();
        LoadDto<T> loadDto = Loader.load(getPathToMainStage());
        controller = loadDto.getController();
        setMenuToScene(loadDto.getScene());
        setScene(loadDto.getScene());
        setTitle(ViewProperties.getInstance().getProperty("mainTitle"));
        setMinWidth(600);
        setMinHeight(400);
    }

    public static void setMenuToScene(Scene scene){
        BorderPane root = new BorderPane();
        root.setTop(new MainMenu());
        root.setCenter(scene.getRoot());
        scene.setRoot(root);
    }

    public abstract String getPathToMainStage();
}
