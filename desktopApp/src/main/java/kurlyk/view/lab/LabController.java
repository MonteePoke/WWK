package kurlyk.view.lab;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import kurlyk.view.fxCommon.Controller;
import org.springframework.stereotype.Component;

@Component
public class LabController extends Controller {

    @FXML private Button CpuButton;



    protected void initialize(){
        CpuButton.setOnAction(event -> {

        });
    }
}
