package kurlyk.view.signIn;

import kurlyk.view.fxCommon.StageEnum;
import kurlyk.view.config.ConfigCreator;
import kurlyk.view.fxCommon.AbstractInitializer;
import kurlyk.view.fxCommon.Initializer;

public class SignInInitializer extends AbstractInitializer<SignInController> {

    @Override
    public Initializer initialize() {
        return super.initialize();
    }

    @Override
    public String getName() {
        return "signIn";
    }

    @Override
    protected void setProperty(){

    }

    @Override
    protected void setHandler(){
        controller.getServerConfigButton().setOnAction(event -> {
            ConfigCreator.setSceneRoot(StageEnum.SIGN_IN_STAGE);
        });

        controller.getLocalConfigButton().setOnKeyPressed(event -> {

        });

        controller.getLocalRunButton().setOnKeyPressed(event -> {

        });

        controller.getServerRunButton().setOnKeyPressed(event -> {

        });
    }
}
