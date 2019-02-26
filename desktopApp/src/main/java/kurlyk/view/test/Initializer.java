package kurlyk.view.test;

import javafx.stage.Stage;

public interface Initializer {

    Initializer initialize();
    Stage getRoot();
    String getName();
}
