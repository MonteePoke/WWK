package kurlyk.view.computerSystemDiagramWindow.characteristicWindow;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import kurlyk.graph.ComputerSystem.ComputerSystemElement;
import kurlyk.view.common.Properties;
import kurlyk.view.common.stage.BaseStage;

public class CharacteristicStage extends BaseStage {


    public CharacteristicStage(ComputerSystemElement computerSystemElement) {
        super();
        CharacteristicController controller = (CharacteristicController) getController();
        controller.addCharacteristic(Properties.getInstance().getProperty("availabilityFactorProperty"),
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
        return "computerSystemDiagramWindow/characteristicWindow/main";
    }
}
