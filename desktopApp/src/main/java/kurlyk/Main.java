package kurlyk;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import kurlyk.view.StageEnum;
import kurlyk.view.StagePool;
import kurlyk.view.signIn.SignInCreator;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage){
        primaryStage.setScene(new Scene(new Group()));
        StagePool.getInstance().addStage(StageEnum.MAIN_STAGE, primaryStage);
        createStages();
        SignInCreator.setSceneRoot(StageEnum.MAIN_STAGE);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    private static void createStages(){

    }
}