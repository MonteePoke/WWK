package kurlyk.view.fxCommon;

import javafx.stage.Stage;

public abstract class BaseStage extends Stage {

    public BaseStage() {
        this.setScene(FxmlLoader.loadScene(getName()));
    }

    public abstract String getName();
}
