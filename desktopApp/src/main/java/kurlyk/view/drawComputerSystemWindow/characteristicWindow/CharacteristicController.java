package kurlyk.view.drawComputerSystemWindow.characteristicWindow;

import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import kurlyk.view.common.Controller;
import kurlyk.view.common.stage.StagePool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CharacteristicController extends Controller {

    @FXML private GridPane characteristicTable;

    @Autowired
    private StagePool stagePool;

    public void initialize(){

    }
}