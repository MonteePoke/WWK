package kurlyk.view.components.labTreeView;

import javafx.scene.control.TreeView;
import kurlyk.communication.Communicator;
import kurlyk.models.Subject;

public class LabTreeView extends TreeView<TreeDto> {
    private CustomTreeItem root;

    public LabTreeView(Communicator communicator) {
        createRootItem();
        setRoot(root);
        setShowRoot(false);
        setCellFactory(e ->
                new CustomTreeCell(communicator, this::addItemToSelected, this::deleteSelectItem, this::getSelectItem)
        );
        createWorld();
    }

    private void createRootItem(){
        TreeDto defaultTreeDto = new TreeDto(TreeDtoType.NONE);
        root = new CustomTreeItem(defaultTreeDto);
        root.setExpanded(true);
    }

    private void addItemToSelected(CustomTreeItem customTreeItem){
        getSelectItem().getChildren().add(customTreeItem);
    }

    private CustomTreeItem getSelectItem(){
        return (CustomTreeItem) getSelectionModel().getSelectedItem();
    }

    private CustomTreeItem deleteSelectItem(){
        CustomTreeItem customTreeItem = (CustomTreeItem) getSelectionModel().getSelectedItem();
        customTreeItem.getParent().getChildren().remove(customTreeItem);
        return customTreeItem;
    }

    private void createWorld(){
        CustomTreeItem subjectItem = new CustomTreeItem(
                new TreeDto(Subject.builder().id(1L).name("ВВК").build())
        );
       root.getChildren().add(subjectItem);
    }
}
