package kurlyk.view.common.stage;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import kurlyk.view.common.dto.LoadInfo;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class Loader implements ApplicationContextAware {

    private static ApplicationContext staticContext;
    private static final String pathToView = "/view/";

    public static LoadInfo load(String viewName){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setControllerFactory(clazz -> staticContext.getBean(clazz));
            loader.setLocation(Loader.class.getResource(pathToView + viewName + ".fxml"));
            loader.setClassLoader(Loader.class.getClassLoader());
            Parent parent = loader.load();
            parent.getStylesheets().add(pathToView + "common.css");
            parent.getStylesheets().add(pathToView + viewName + ".css");
            return new LoadInfo(){{
                setScene(new Scene(parent));
                setController(loader.getController());
            }};
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        Loader.staticContext = context;
    }
}
