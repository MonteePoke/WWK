package kurlyk.view.components.labTreeView;

import javafx.scene.control.TreeView;
import kurlyk.communication.Communicator;
import kurlyk.models.Subject;

import java.io.IOException;

public class LabTreeView extends TreeView<TreeDto> {
    private CustomTreeItem root;

    public LabTreeView(Communicator communicator) {
        createRootItem();
        setRoot(root);
        setShowRoot(false);
        setCellFactory(e ->
                new CustomTreeCell(communicator, this::addItem, this::deleteItem)
        );
        createWorld(communicator);
    }

    private void createRootItem(){
        TreeDto defaultTreeDto = new TreeDto(TreeDtoType.NONE);
        root = new CustomTreeItem(defaultTreeDto);
        root.setExpanded(true);
    }

    private void addItem(CustomTreeItem root, CustomTreeItem customTreeItem){
        root.getChildren().add(customTreeItem);
    }

    private CustomTreeItem deleteItem(CustomTreeItem customTreeItem){
        customTreeItem.getParent().getChildren().remove(customTreeItem);
        return customTreeItem;
    }

    private void createWorld(Communicator communicator){
        CustomTreeItem subjectItem = new CustomTreeItem(
                new TreeDto(Subject.builder().id(1L).name("ВВК").build())
        );
        root.getChildren().add(subjectItem);

        getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            checkItemChildren((CustomTreeItem) newValue, communicator);
        });
    }

    static void checkItemChildren(CustomTreeItem item, Communicator communicator){
        if(item.getChildren().size() == 0 && !item.isChildrenIsSetted()){
            item.setChildrenIsSetted(true);
            try {
                switch (item.getValue().getType()){
                    case SUBJECT:
                        item.getChildren().addAll(
                                TreeDto.itemsOfLabWorks(communicator.getLabWorks())
                        );
                        break;

                    case LAB_WORK:
                        item.getChildren().addAll(
                                TreeDto.itemsOfTasks(communicator.getTasks(item.getValue().getLabWork()))
                        );
                        break;
                    case TASK:
                        item.getChildren().addAll(
                                TreeDto.itemsOfQuestions(communicator.getQuestionHeaders(item.getValue().getTask()))
                        );
                        break;
                    case QUESTION:
                        break;
                    case NONE:
                        break;
                    default:
                        throw new RuntimeException("Неизвестный тип элемента дерева");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
