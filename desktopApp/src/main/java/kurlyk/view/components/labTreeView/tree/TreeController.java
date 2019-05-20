package kurlyk.view.components.labTreeView.tree;

import javafx.fxml.FXML;
import javafx.scene.layout.HBox;
import kurlyk.communication.Communicator;
import kurlyk.view.common.controller.Controller;
import kurlyk.view.common.stage.StagePool;
import kurlyk.view.components.labTreeView.LabTreeView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class TreeController extends Controller {
    @FXML private HBox root;

    @Autowired
    private StagePool stagePool;

    @Autowired
    private Communicator communicator;


    public void initialize() {
        root.getChildren().add(new LabTreeView(communicator, stagePool));
    }
}