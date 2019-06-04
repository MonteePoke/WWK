package kurlyk.view.common.stage.base;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import kurlyk.view.common.ViewProperties;
import kurlyk.view.common.controller.Controller;
import kurlyk.view.common.dto.BaseStageDto;
import kurlyk.view.common.dto.LoadDto;
import kurlyk.view.common.stage.Loader;
import kurlyk.view.components.menu.MainMenu;
import kurlyk.view.components.toolbar.ToolbarSceneCreator;

public abstract class BaseStage <T extends Controller> extends Stage {

    protected T controller;
    protected BorderPane root;
    protected MainMenu mainMenu;

    public BaseStage() {
        this(BaseStageDto.allInclusive());
    }

    public BaseStage(BaseStageDto baseStageDto) {
        super();
        LoadDto<T> loadDto = Loader.load(getPathToMainStage());
        controller = loadDto.getController();
        if (baseStageDto.isNeedMenu()) {
            setScene(createSceneBoxTemplate(loadDto.getScene()));
        } else {
            setScene(loadDto.getScene());
        }
        setTitle(ViewProperties.getInstance().getProperty("mainTitle"));
        setMinWidth(800);
        setMinHeight(400);
        if (baseStageDto.isNeedMenu()) {
            mainMenu = setMenuToScene(root);
        }

    }

    private Scene createSceneBoxTemplate(Scene scene){
        root = new BorderPane();
        root.setCenter(scene.getRoot());
        scene.setRoot(root);
        return scene;
    }

    public static MainMenu setMenuToScene(BorderPane root){
        MainMenu mainMenu = new MainMenu();
        root.setTop(new VBox(
                mainMenu,
                new ToolbarSceneCreator().getScene().getRoot()
        ));
        return mainMenu;
    }

    public abstract String getPathToMainStage();

    public MainMenu getMainMenu() {
        return mainMenu;
    }
}
