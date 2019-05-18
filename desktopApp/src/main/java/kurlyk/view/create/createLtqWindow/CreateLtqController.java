package kurlyk.view.create.createLtqWindow;

import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import kurlyk.communication.Communicator;
import kurlyk.communication.UserInfo;
import kurlyk.view.common.controller.Controller;
import kurlyk.view.common.stage.StagePool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class CreateLtqController extends Controller {

    @FXML private VBox root;

    @Autowired
    private StagePool stagePool;

    @Autowired
    private UserInfo userInfo;

    @Autowired
    private Communicator communicator;


    public void initialize(){
    }

    public void createLabWork(){

    }

    public void createTask(){

    }

    public void createQuestion(){

    }
}