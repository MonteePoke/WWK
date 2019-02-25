package kurlyk.view.signIn;

import kurlyk.view.StageEnum;
import kurlyk.view.config.ConfigCreator;
import kurlyk.view.fxCommon.AbstractInitializer;
import kurlyk.view.fxCommon.IInitializer;

public class SignInInitializer extends AbstractInitializer<SignInController> {

    @Override
    public IInitializer initialize() {
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
            ConfigCreator.setSceneRoot(StageEnum.MAIN_STAGE);
        });

        controller.getLocalConfigButton().setOnKeyPressed(event -> {

        });

        controller.getLocalRunButton().setOnKeyPressed(event -> {

        });

        controller.getServerRunButton().setOnKeyPressed(event -> {

        });
    }
}
