package kurlyk.view.fxCommon;

import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import java.io.IOException;

@Component
public class FxmlLoader implements ApplicationContextAware {

    private static ApplicationContext staticContext;
    private static final String pathToMarkupPath = "/view/";
    private static final String pathToStylePath = "/style/";

    private static Parent load(String viewName) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(FxmlLoader.class.getResource(pathToMarkupPath + viewName + ".fxml"));
        loader.setClassLoader(FxmlLoader.class.getClassLoader());
        Parent parent = loader.load(FxmlLoader.class.getResourceAsStream(pathToMarkupPath + viewName + ".fxml"));
        parent.getStylesheets().add(pathToStylePath + "common.css");
        parent.getStylesheets().add(pathToStylePath + viewName + ".css");
        return parent;
    }

    public static Scene loadScene(String viewName){
        Scene scene = new Scene(new Group());
        try {
            scene.setRoot(load(viewName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return scene;
    }

    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        FxmlLoader.staticContext = context;
    }
}