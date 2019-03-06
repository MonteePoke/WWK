package kurlyk;

import javafx.application.Application;
import javafx.stage.Stage;
import kurlyk.view.common.stage.StagePool;
import kurlyk.view.common.stage.Stages;
import kurlyk.view.signIn.SignInStage;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class Main extends Application {

    private static AnnotationConfigApplicationContext context;

    @Override
    public void init() throws Exception {
        context = new AnnotationConfigApplicationContext(DesktopAppContext.class);
    }

    @Override
    public void start(Stage primaryStage){
        SignInStage signInStage = new SignInStage();
        StagePool stagePool = (StagePool) context.getBean("stagePool");
        stagePool.pushStage(Stages.SIGN_IN, signInStage);
        signInStage.show();
    }

    @Override
    public void stop() {
        context.close();
    }

    public static void main(String[] args) {
        launch(args);
    }
}