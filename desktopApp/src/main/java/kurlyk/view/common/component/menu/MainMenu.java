package kurlyk.view.common.component.menu;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import kurlyk.view.common.ViewProperties;

public class MainMenu extends MenuBar {

    public MainMenu() {
        Menu actionsMenu = new Menu(ViewProperties.getInstance().getProperty("actions"));
        Menu helpMenu = new Menu(ViewProperties.getInstance().getProperty("help"));
        Menu exitMenu = new Menu(ViewProperties.getInstance().getProperty("exit"));

        actionsMenu.getItems().addAll(
                new MenuItem(ViewProperties.getInstance().getProperty("something"))
        );

        getMenus().addAll(actionsMenu, helpMenu, exitMenu);
    }
}
