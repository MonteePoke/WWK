package kurlyk.view.common.stage.base;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import kurlyk.view.common.ViewProperties;
import kurlyk.view.common.controller.Controller;
import kurlyk.view.common.dto.BaseStageDto;
import kurlyk.view.common.dto.LoadDto;
import kurlyk.view.common.stage.Loader;
import kurlyk.view.components.labTreeView.tree.TreeSceneCreator;
import kurlyk.view.components.menu.MainMenu;
import kurlyk.view.components.toolbar.ToolbarSceneCreator;

public abstract class BaseStage <T extends Controller> extends Stage {

    protected T controller;
    protected BorderPane root;

    public BaseStage() {
        this(BaseStageDto.allInclusive());
    }

    public BaseStage(BaseStageDto baseStageDto) {
        super();
        LoadDto<T> loadDto = Loader.load(getPathToMainStage());
        controller = loadDto.getController();
        if (baseStageDto.isNeedMenu() || baseStageDto.isNeedTree()) {
            setScene(createSceneBoxTemplate(loadDto.getScene()));
        } else {
            setScene(loadDto.getScene());
        }
        setTitle(ViewProperties.getInstance().getProperty("mainTitle"));
        setMinWidth(800);
        setMinHeight(400);
        if (baseStageDto.isNeedMenu()) {
            setMenuToScene(root);
        }
        if (baseStageDto.isNeedTree()) {
            setTreeViewToScene(root);
        }
    }

    private Scene createSceneBoxTemplate(Scene scene){
        root = new BorderPane();
        root.setCenter(scene.getRoot());
        scene.setRoot(root);
        return scene;
    }

    public static void setMenuToScene(BorderPane root){
        root.setTop(new VBox(
                new MainMenu(),
                new ToolbarSceneCreator().getScene().getRoot()
        ));
    }

    public static void setTreeViewToScene(BorderPane root){
        HBox parent = (HBox) new TreeSceneCreator().getScene().getRoot();
        parent.prefHeightProperty().bind(root.heightProperty());
        root.setLeft(parent);
    }

    public abstract String getPathToMainStage();
}
