package kurlyk.view.common.component;

import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import kurlyk.view.common.Properties;


public class DiagramContextMenu extends ContextMenu {

    private MenuItem delete;
    private MenuItem showCharacteristics;
    private MenuItem connect;

    public DiagramContextMenu() {
        super();
        delete = new MenuItem(Properties.getInstance().getProperty("delete"));
        showCharacteristics = new MenuItem(Properties.getInstance().getProperty("showCharacteristics"));
        connect = new MenuItem(Properties.getInstance().getProperty("connect"));
    }

    public void setDeleteAction(Runnable action){
        getItems().add(delete);
        delete.setOnAction(event -> action.run());
    }

    public void setShowCharacteristicsAction(Runnable action){
        getItems().add(showCharacteristics);
        showCharacteristics.setOnAction(event -> action.run());
    }

    public void setConnectAction(Runnable action){
        getItems().add(connect);
        connect.setOnAction(event -> action.run());
    }
}
