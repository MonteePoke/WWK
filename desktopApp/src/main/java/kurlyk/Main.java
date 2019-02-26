package kurlyk;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import kurlyk.view.fxCommon.FxmlLoader;
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
        FxmlLoader renderer = context.getBean(FxmlLoader.class);

//        primaryStage.setScene(new Scene(new SignInInitializer().initialize().getRoot()));
        SignInStage signInStage = new SignInStage();
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