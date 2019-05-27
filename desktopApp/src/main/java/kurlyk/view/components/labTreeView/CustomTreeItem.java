package kurlyk.view.components.labTreeView;

import javafx.scene.control.TreeItem;

public class CustomTreeItem extends TreeItem<TreeDto> {
    private boolean childrenIsSetted;
    private CustomTreeItem itemParent;

    public CustomTreeItem(CustomTreeItem itemParent, TreeDto value) {
        super(value);
        this.childrenIsSetted = false;
        this.itemParent = itemParent;
    }

    public boolean isChildrenIsSetted() {
        return childrenIsSetted;
    }

    public void setChildrenIsSetted(boolean childrenIsSetted) {
        this.childrenIsSetted = childrenIsSetted;
    }

    public CustomTreeItem getItemParent() {
        return itemParent;
    }
}
