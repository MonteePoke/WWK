package kurlyk.view.components.menu;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.stage.Modality;
import kurlyk.view.common.ViewProperties;
import kurlyk.view.create.createTaskWindow.questionListWindow.QuestionListStage;

public class MainMenu extends MenuBar {

    private MenuItem dataBaseItem;
    private MenuItem labWorksItem;
    private MenuItem showAnswerItem;

    public MainMenu() {
        Menu openMenu = new Menu(ViewProperties.getInstance().getProperty("open"));
        Menu helpMenu = new Menu(ViewProperties.getInstance().getProperty("help"));
        Menu exitMenu = new Menu(ViewProperties.getInstance().getProperty("exit"));

        openMenu.getItems().addAll(
                labWorksItem = new MenuItem(ViewProperties.getInstance().getProperty("labWorks")),
                dataBaseItem = new MenuItem(ViewProperties.getInstance().getProperty("dataBase")),
                showAnswerItem = new MenuItem("Показать ответ")
        );

        dataBaseItem.setOnAction(event -> {
            QuestionListStage questionListStage = new QuestionListStage((question -> { }));
            questionListStage.initModality(Modality.APPLICATION_MODAL);
            questionListStage.showAndWait();
        });

        getMenus().addAll(openMenu, helpMenu, exitMenu);
    }

    public MenuItem getDataBaseItem() {
        return dataBaseItem;
    }

    public MenuItem getLabWorksItem() {
        return labWorksItem;
    }

    public MenuItem getShowAnswerItem() {
        return showAnswerItem;
    }
}
