package kurlyk.view.computerSystemDiagramWindow.characteristicWindow;

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
    }


    @Override
    public String getPathToMainStage() {
        return "computerSystemDiagramWindow/characteristicWindow/main";
    }
}
