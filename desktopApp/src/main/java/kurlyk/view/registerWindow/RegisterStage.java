package kurlyk.view.registerWindow;

import javafx.scene.input.KeyCode;
import kurlyk.view.common.dto.BaseStageDto;
import kurlyk.view.common.stage.base.BaseStage;


public class RegisterStage extends BaseStage<RegisterController> {

    public RegisterStage() {
        super(BaseStageDto.allOff());
        setMinWidth(0);
        setMinHeight(0);
        addEventHandler(javafx.scene.input.KeyEvent.KEY_RELEASED, event -> {
            if (KeyCode.ENTER == event.getCode()) {
                controller.register();
            }
        });
    }

    @Override
    public String getPathToMainStage() {
        return "registerWindow/main";
    }
}
