package kurlyk.view.common.stage.labTree;

import kurlyk.view.common.controller.Controller;
import kurlyk.view.common.stage.base.BaseSceneCreator;

public abstract class LabTreeSceneCreator<T extends Controller> extends BaseSceneCreator<T> {


    public LabTreeSceneCreator() {
        super(true);
        LabTreeStage.setTreeViewToScene(scene);
    }
}
