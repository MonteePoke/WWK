package kurlyk;

import javafx.application.Application;
import javafx.stage.Stage;
import kurlyk.view.common.stage.StagePool;
import kurlyk.view.common.stage.Stages;
import kurlyk.view.signInWindow.SignInStage;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class Main extends Application {

    private static StagePool stagePool;
    private static AnnotationConfigApplicationContext context;

    @Override
    public void init() throws Exception {
        context = new AnnotationConfigApplicationContext(DesktopAppContext.class);
    }

    @Override
    public void start(Stage primaryStage){
        stagePool = (StagePool) context.getBean("stagePool");
        stagePool.pushStageAndShow(Stages.SIGN_IN, new SignInStage());
    }

    public static StagePool getStagePool() {
        return stagePool;
    }

    @Override
    public void stop() {
        context.close();
    }

    public static void main(String[] args) {
        launch(args);
    }
}