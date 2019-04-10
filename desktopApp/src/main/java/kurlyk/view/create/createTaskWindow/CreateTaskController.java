package kurlyk.view.create.createTaskWindow;

import kurlyk.communication.UserInfo;
import kurlyk.view.common.controller.Controller;
import kurlyk.view.common.stage.StagePool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class CreateTaskController extends Controller {



    @Autowired
    private StagePool stagePool;

    @Autowired
    private UserInfo userInfo;


    public void initialize() {

    }
}