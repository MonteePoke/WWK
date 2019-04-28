package kurlyk.view.common.component.labTreeView;

import javafx.scene.control.TreeView;

public class LabTreeView extends TreeView<TreeDto> {
    private CustomTreeItem root;

    public LabTreeView() {
        setRoot(root = createRootItem());
    }

    private CustomTreeItem createRootItem(){
        TreeDto defaultTreeDto = new TreeDto(-1L, TreeDtoType.NONE, "ВВК");
        CustomTreeItem rootItem = new CustomTreeItem(defaultTreeDto);
        rootItem.setExpanded(true);
        return rootItem;
    }

    private void add(CustomTreeItem parent, CustomTreeItem children){
        parent.getChildren().add(children);
    }
}
