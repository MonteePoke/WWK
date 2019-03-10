package kurlyk.view.common.dto;

import javafx.scene.Scene;
import kurlyk.view.common.Controller;
import lombok.Data;

@Data
public class LoadInfo {
    private Controller controller;
    private Scene scene;
}
