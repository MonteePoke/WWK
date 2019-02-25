package kurlyk.view.fxCommon;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import kurlyk.exeption.InitializatorExeption;

public abstract class AbstractInitializer <T> implements IInitializer{

    protected FXMLLoader loader;
    protected T controller;

    private final String prefixToMarkupPath = "view/";
    private final String sufixToMarkupPath = ".fxml";

    public IInitializer initialize() {
        try {
            loader = new FXMLLoader(getClass().getClassLoader().getResource(
                    prefixToMarkupPath +
                            getName() +
                            sufixToMarkupPath)
            );
            loader.load();
            controller = loader.getController();
        } catch (Exception e) {
            throw new InitializatorExeption(getClass().getSimpleName(), e);
        }
        setProperty();
        setHandler();
        return this;
    }

    public Parent getRoot(){
        return loader.getRoot();
    }

    public abstract String getName();
    protected abstract void setProperty();
    protected abstract void setHandler();
}
