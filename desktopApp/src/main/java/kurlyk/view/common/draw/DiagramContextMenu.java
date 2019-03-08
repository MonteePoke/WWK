package kurlyk.view.common.draw;

import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import kurlyk.view.common.Properties;


public class DiagramContextMenu extends ContextMenu {

    private MenuItem delete;

    public DiagramContextMenu() {
        super();
        delete = new MenuItem(Properties.getInstance().getProperty("delete"));
        getItems().addAll(delete);
    }

    public void setDeleteAction(Runnable deleteAction){
        delete.setOnAction(event -> {
            deleteAction.run();
        });
    }
}
