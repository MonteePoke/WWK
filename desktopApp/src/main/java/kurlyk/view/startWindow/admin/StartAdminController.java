package kurlyk.view.startWindow.admin;

import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import kurlyk.communication.Communicator;
import kurlyk.view.common.controller.Controller;
import kurlyk.view.common.stage.StagePool;
import kurlyk.view.components.labTreeView.LabTreeView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class StartAdminController extends Controller {


    @FXML private VBox root;

    @Autowired
    private StagePool stagePool;

    @Autowired
    private Communicator communicator;


    public void initialize(){
        LabTreeView labTreeView = new LabTreeView(communicator, stagePool);
        root.getChildren().add(labTreeView);
    }
}