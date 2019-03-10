package kurlyk.view.drawComputerSystemWindow.characteristicWindow;

import kurlyk.view.common.Properties;
import kurlyk.view.common.stage.BaseStage;
import kurlyk.view.drawComputerSystemWindow.computerSystemDiagram.ComputerSystemElementProperty;

public class CharacteristicStage extends BaseStage {


    public CharacteristicStage(ComputerSystemElementProperty computerSystemElementProperty) {
        super();
        CharacteristicController controller = (CharacteristicController) getController();
        controller.addCharacteristic(Properties.getInstance().getProperty("availabilityFactorProperty"),
                computerSystemElementProperty.getAvailabilityFactorProperty()
        );
    }


    @Override
    public String getPathToMainStage() {
        return "drawComputerSystemWindow/characteristicWindow/main";
    }
}
