package kurlyk.view.task.computerSystemDiagramWindow.characteristicWindow;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import kurlyk.graph.ComputerSystem.ComputerSystemElement;
import kurlyk.view.common.ViewProperties;
import kurlyk.view.common.stage.BaseStage;

public class CharacteristicStage extends BaseStage<CharacteristicController> {


    public CharacteristicStage(ComputerSystemElement computerSystemElement) {
        super();
        controller.addCharacteristic(ViewProperties.getInstance().getProperty("availabilityFactorProperty"),
                computerSystemElement.getAvailabilityFactor()
        );
        addEventHandler(KeyEvent.KEY_RELEASED, event -> {
            if (KeyCode.ENTER == event.getCode()) {
                close();
            }
        });
    }


    @Override
    public String getPathToMainStage() {
        return "task/computerSystemDiagramWindow/characteristicWindow/main";
    }
}
