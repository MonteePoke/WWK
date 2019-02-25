package kurlyk.view.fxCommon;

import javafx.scene.Parent;

public interface IInitializer {

    IInitializer initialize();
    Parent getRoot();
    String getName();
}
