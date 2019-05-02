package kurlyk.view.components.menu;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import kurlyk.view.common.ViewProperties;

public class MainMenu extends MenuBar {

    public MainMenu() {
        Menu openMenu = new Menu(ViewProperties.getInstance().getProperty("open"));
        Menu helpMenu = new Menu(ViewProperties.getInstance().getProperty("help"));
        Menu exitMenu = new Menu(ViewProperties.getInstance().getProperty("exit"));

        openMenu.getItems().addAll(
                new MenuItem(ViewProperties.getInstance().getProperty("labWorks")),
                new MenuItem(ViewProperties.getInstance().getProperty("dataBase"))
        );

        getMenus().addAll(openMenu, helpMenu, exitMenu);
    }
}
