package kurlyk.view.components.menu;

import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.stage.Modality;
import kurlyk.view.common.ViewProperties;
import kurlyk.view.create.questionListWindow.QuestionListStage;

public class MainMenu extends MenuBar {

    private MenuItem dataBaseItem;
    private MenuItem labWorksItem;
    private MenuItem showAnswerItem;
    private MenuItem competences;

    public MainMenu() {
        Menu openMenu = new Menu(ViewProperties.getInstance().getProperty("open"));
        Menu helpMenu = new Menu(ViewProperties.getInstance().getProperty("help"));
        Menu exitMenu = new Menu();

        openMenu.getItems().addAll(
                labWorksItem = new MenuItem(ViewProperties.getInstance().getProperty("labWorks")),
                dataBaseItem = new MenuItem(ViewProperties.getInstance().getProperty("dataBase")),
                competences = new MenuItem("Компетенции"),
                showAnswerItem = new MenuItem("Показать ответ")
        );

        dataBaseItem.setOnAction(event -> {
            QuestionListStage questionListStage = new QuestionListStage(question -> {}, question -> {});
            questionListStage.initModality(Modality.APPLICATION_MODAL);
            questionListStage.showAndWait();
        });

        competences.setOnAction(event -> {
//            CompetenceListStage competenceListStage = new CompetenceListStage();
//            competenceListStage.initModality(Modality.APPLICATION_MODAL);
//            competenceListStage.showAndWait();
        });

        Label exitMenuLabel = new Label(ViewProperties.getInstance().getProperty("exit"));
        exitMenuLabel.setOnMouseClicked(event -> Platform.exit());
        exitMenu.setGraphic(exitMenuLabel);

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
