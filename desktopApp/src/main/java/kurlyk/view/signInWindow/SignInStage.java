package kurlyk.view.signInWindow;

import javafx.scene.input.KeyCode;
import kurlyk.view.common.stage.base.BaseStage;

public class SignInStage extends BaseStage<SignInController> {

    public SignInStage() {
        super();
        setMinWidth(0);
        setMinHeight(0);
        addEventHandler(javafx.scene.input.KeyEvent.KEY_RELEASED, event -> {
            if (KeyCode.ENTER == event.getCode()) {
                controller.signIn();
            }
        });
    }

    @Override
    public String getPathToMainStage() {
        return "signInWindow/main";
    }
}
