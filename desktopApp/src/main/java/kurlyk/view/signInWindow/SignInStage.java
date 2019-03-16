package kurlyk.view.signInWindow;

import kurlyk.view.common.stage.BaseStage;

public class SignInStage extends BaseStage<SignInController> {


    public SignInStage() {
        super();
    }

    @Override
    public String getPathToMainStage() {
        return "signInWindow/main";
    }
}
