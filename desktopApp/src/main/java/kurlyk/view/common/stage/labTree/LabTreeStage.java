package kurlyk.view.common.stage.labTree;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import kurlyk.view.common.component.labTreeView.LabTreeView;
import kurlyk.view.common.controller.Controller;
import kurlyk.view.common.stage.base.BaseStage;

public abstract class LabTreeStage<T extends Controller> extends BaseStage<T> {

    protected T controller;

    public LabTreeStage() {
        super(true);
        setTreeViewToScene(getScene());
    }

    public static void setTreeViewToScene(Scene scene){
        BorderPane root = new BorderPane();
        root.setLeft(new LabTreeView());
        root.setCenter(scene.getRoot());
        scene.setRoot(root);
    }
}
