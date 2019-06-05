package kurlyk.view.components.labTreeView;

import javafx.scene.control.TreeView;
import kurlyk.WorkEntityType;
import kurlyk.communication.Communicator;
import kurlyk.models.LabWork;
import kurlyk.models.Question;
import kurlyk.models.Subject;
import kurlyk.models.Task;
import kurlyk.view.common.stage.StagePool;

import java.io.IOException;
import java.util.Comparator;
import java.util.stream.Collectors;

public class LabTreeView extends TreeView<TreeDto> {
    private CustomTreeItem root;

    public LabTreeView(Communicator communicator, StagePool stagePool) {
        createRootItem();
        setRoot(root);
        setShowRoot(false);
        setCellFactory(e ->
                new CustomTreeCell(communicator, stagePool, this::addItem, this::deleteItem)
        );
        createWorld(communicator);
    }

    private void createRootItem(){
        TreeDto defaultTreeDto = new TreeDto(WorkEntityType.NONE);
        root = new CustomTreeItem(null, defaultTreeDto);
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
                null,
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
                                TreeDto.itemsOfLabWorks(
                                        item,
                                        communicator.getLabWorks()
                                                .stream()
                                                .sorted(Comparator.comparing(LabWork::getNumber))
                                                .collect(Collectors.toList())
                                )
                        );
                        break;
                    case LAB_WORK:
                        item.getChildren().addAll(
                                TreeDto.itemsOfTasks(
                                        item,
                                        communicator.getTasks(item.getValue().getLabWork())
                                                .stream()
                                                .sorted(Comparator.comparing(Task::getNumber))
                                                .collect(Collectors.toList())
                                )
                        );
                        break;
                    case TASK:
                        item.getChildren().addAll(
                                TreeDto.itemsOfQuestions(
                                        item,
                                        communicator.getQuestionHeaders(item.getValue().getTask())
                                                .stream()
                                                .sorted(Comparator.comparing(Question::getNumber))
                                                .collect(Collectors.toList())
                                )
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
