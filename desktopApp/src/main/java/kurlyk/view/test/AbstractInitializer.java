package kurlyk.view.test;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import kurlyk.exeption.InitializatorExeption;

public abstract class AbstractInitializer <T> implements Initializer {

    protected FXMLLoader loader;
    protected T controller;

    private final String prefixToMarkupPath = "view/";
    private final String sufixToMarkupPath = ".fxml";
    private final String prefixToStylePath = "style/";
    private final String sufixToStylePath = ".css";
    private final String commonStylesheets = "common";

    public Initializer initialize() {
        try {
            loader = new FXMLLoader(getClass().getClassLoader().getResource(
                    prefixToMarkupPath +
                            getName() +
                            sufixToMarkupPath)
            );
            loader.load();
            Stage stage = loader.getRoot();
            stage.getScene().getStylesheets().add(prefixToStylePath + commonStylesheets + sufixToStylePath);
            stage.getScene().getStylesheets().add(prefixToStylePath + getName() + sufixToStylePath);
            controller = loader.getController();
        } catch (Exception e) {
            throw new InitializatorExeption(e);
        }
        setProperty();
        setHandler();
        return this;
    }

    public Stage getRoot(){
        return loader.getRoot();
    }

    public abstract String getName();
    protected abstract void setProperty();
    protected abstract void setHandler();
}