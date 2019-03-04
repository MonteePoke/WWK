package kurlyk.view.lab;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import kurlyk.view.fxCommon.Controller;
import kurlyk.view.fxCommon.StagePool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LabController extends Controller {

    @FXML private Button CpuButton;

    @Autowired
    private StagePool stagePool;


    protected void initialize(){
        CpuButton.setOnAction(event -> {

//            root.setOnMouseMoved(e -> {
//                button.setLayoutX(e.getSceneX());
//                button.setLayoutY(e.getSceneY());
//            });
        });
    }
}
