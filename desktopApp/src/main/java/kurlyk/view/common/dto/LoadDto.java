package kurlyk.view.common.dto;

import javafx.scene.Scene;
import kurlyk.view.common.controller.Controller;
import lombok.EqualsAndHashCode;
import lombok.Setter;

import java.io.Serializable;

@EqualsAndHashCode
@Setter
public class LoadDto<T extends Controller> implements Serializable {
    private T controller;
    private Scene scene;

    public T getController() {
        return controller;
    }

    public Scene getScene() {
        return scene;
    }
}
