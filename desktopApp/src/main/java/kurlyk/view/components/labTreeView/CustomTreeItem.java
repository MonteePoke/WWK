package kurlyk.view.components.labTreeView;

import javafx.scene.control.TreeItem;

public class CustomTreeItem extends TreeItem<TreeDto> {
    private boolean childrenIsSetted;

    public CustomTreeItem(TreeDto value) {
        super(value);
        childrenIsSetted = false;
    }

    public boolean isChildrenIsSetted() {
        return childrenIsSetted;
    }

    public void setChildrenIsSetted(boolean childrenIsSetted) {
        this.childrenIsSetted = childrenIsSetted;
    }
}
