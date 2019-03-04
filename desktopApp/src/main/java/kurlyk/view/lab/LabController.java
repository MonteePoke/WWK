package kurlyk.view.lab;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import kurlyk.view.computerSystemDiagram.ComputerSystemPicture;
import kurlyk.view.computerSystemDiagram.ComputerSystemPicturePathEnum;
import kurlyk.view.fxCommon.Controller;
import kurlyk.view.fxCommon.StagePool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LabController extends Controller {

    @FXML private Button CpuButton;
    @FXML private VBox root;

    @Autowired
    private StagePool stagePool;


    protected void initialize(){
        CpuButton.setOnAction(event -> {
            System.out.println("CpuButton is push");
            ComputerSystemPicture picture = new ComputerSystemPicture(ComputerSystemPicturePathEnum.CPU);
            root.getChildren().add(picture);
            CpuButton.getScene().getRoot().setOnMouseMoved(e -> {
                picture.setLayoutX(e.getSceneX());
                picture.setLayoutY(e.getSceneY());
            });
        });
    }
}
