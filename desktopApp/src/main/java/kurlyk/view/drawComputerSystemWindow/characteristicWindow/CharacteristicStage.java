package kurlyk.view.drawComputerSystemWindow.characteristicWindow;

import kurlyk.view.common.Properties;
import kurlyk.view.common.stage.BaseStage;
import kurlyk.view.drawComputerSystemWindow.computerSystemDiagram.ComputerSystemDiagramElementProperty;

public class CharacteristicStage extends BaseStage {


    public CharacteristicStage(ComputerSystemDiagramElementProperty computerSystemDiagramElementProperty) {
        super();
        CharacteristicController controller = (CharacteristicController) getController();
        controller.addCharacteristic(Properties.getInstance().getProperty("availabilityFactorProperty"),
                computerSystemDiagramElementProperty.getAvailabilityFactorProperty()
        );
    }


    @Override
    public String getPathToMainStage() {
        return "drawComputerSystemWindow/characteristicWindow/main";
    }
}
