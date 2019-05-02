package kurlyk.view.components;

import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import kurlyk.view.common.ViewProperties;


public class DiagramContextMenu extends ContextMenu {

    private MenuItem delete;
    private MenuItem showCharacteristics;
    private MenuItem connect;

    public DiagramContextMenu() {
        super();
        delete = new MenuItem(ViewProperties.getInstance().getProperty("delete"));
        showCharacteristics = new MenuItem(ViewProperties.getInstance().getProperty("showCharacteristics"));
        connect = new MenuItem(ViewProperties.getInstance().getProperty("connect"));
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
