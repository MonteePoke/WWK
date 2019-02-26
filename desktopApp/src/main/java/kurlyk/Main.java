package kurlyk;

import javafx.application.Application;
import javafx.stage.Stage;
import kurlyk.view.fxCommon.StageEnum;
import kurlyk.view.fxCommon.StagePool;
import kurlyk.view.signIn.SignInInitializer;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage){
        createStages();
        StagePool.getInstance().showStage(StageEnum.SIGN_IN_STAGE);
    }

    private static void createStages(){
        StagePool.getInstance().addStage(StageEnum.SIGN_IN_STAGE, new SignInInitializer().initialize().getRoot());
    }

    public static void main(String[] args) {
        launch(args);
    }
}