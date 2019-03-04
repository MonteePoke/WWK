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
    private static final String pathToView = "/view/";

    private static Parent load(String viewName) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setControllerFactory(clazz -> staticContext.getBean(clazz));
        loader.setLocation(FxmlLoader.class.getResource(pathToView + viewName + ".fxml"));
        loader.setClassLoader(FxmlLoader.class.getClassLoader());
        Parent parent = loader.load();
        parent.getStylesheets().add(pathToView + "common.css");
        parent.getStylesheets().add(pathToView + viewName + ".css");
        return parent;
    }

    public static Parent loadParent(String viewName){
        Parent parent = new Group();
        try {
            parent = load(viewName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return parent;
    }

    public static Scene loadScene(String viewName){
        return new Scene(loadParent(viewName));
    }

    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        FxmlLoader.staticContext = context;
    }
}
