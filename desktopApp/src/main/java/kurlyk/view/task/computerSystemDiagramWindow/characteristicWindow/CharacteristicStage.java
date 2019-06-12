package kurlyk.view.task.computerSystemDiagramWindow.characteristicWindow;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import kurlyk.common.algorithm.graph.ComputerSystem.ComputerSystemElement;
import kurlyk.view.common.ViewProperties;
import kurlyk.view.common.dto.BaseStageDto;
import kurlyk.view.common.stage.base.BaseStage;

public class CharacteristicStage extends BaseStage<CharacteristicController> {


    public CharacteristicStage(ComputerSystemElement computerSystemElement) {
        super(BaseStageDto.allOff());
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
