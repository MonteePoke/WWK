package kurlyk.view.components.labTreeView;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TreeCell;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import kurlyk.communication.Communicator;
import kurlyk.models.*;

import java.io.IOException;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class CustomTreeCell extends TreeCell<TreeDto> {
    private HBox cellBox;
    private Label labelForNumber;
    private Label labelForName;
    private Button addButton;
    private Button deleteButton;
    private Button editButton;
    private Communicator communicator;
    private Consumer<CustomTreeItem> addItem;
    private Supplier<CustomTreeItem> deleteItem;

    public CustomTreeCell(
            Communicator communicator,
            BiConsumer<CustomTreeItem, CustomTreeItem> addItem,
            Function<CustomTreeItem, CustomTreeItem> deleteItem) {
        this.communicator = communicator;
        this.addItem = (customTreeItem) -> addItem.accept(getSelectedItem(), customTreeItem);
        this.deleteItem = () -> deleteItem.apply(getSelectedItem());
    }

    private CustomTreeItem getSelectedItem(){
        return (CustomTreeItem) getTreeItem();
    }

    private CustomTreeItem getParentSelectedItem(){
        return (CustomTreeItem) getTreeItem().getParent();
    }


    @Override
    protected void updateItem(TreeDto item, boolean empty) {
        super.updateItem(item, empty);
        if (isEmpty()) {
            setGraphic(null);
            setText(null);
        } else {
            cellBox = new HBox(10);
            cellBox.setAlignment(Pos.CENTER_LEFT);
            labelForNumber = new Label("");
            labelForNumber.setFont(Font.font("Serif Regular", 15));
            labelForName = new Label("");
            labelForName.setFont(Font.font("Serif Regular", 15));
            addButton = new Button("");
            deleteButton = new Button("");
            editButton = new Button("");
            settings(item);
            setGraphic(cellBox);
            setText(null);
        }
    }

    private void settings(TreeDto treeDto){
        addButton.setGraphic(ButtonPictures.ADD.getImageView());
        deleteButton.setGraphic(ButtonPictures.DELETE.getImageView());
        editButton.setGraphic(ButtonPictures.EDIT.getImageView());
        switch (treeDto.getType()){
            case SUBJECT:
                subjectSettings(treeDto.getSubject());
                break;
            case LAB_WORK:
                labWorkSettings(treeDto.getLabWork());
                break;
            case TASK:
                taskSettings(treeDto.getTask());
                break;
            case QUESTION:
                questionSettings(treeDto.getQuestion());
                break;
            case NONE:
                noneSettings();
                break;
                default:
                    throw new RuntimeException("Неизвестный тип элемента дерева");
        }
    }

    private void subjectSettings(Subject subject){
        cellBox.getChildren().addAll(labelForName, addButton);
        labelForName.setText(subject.getName());
        addButton.setOnAction(event -> {
            LabTreeView.checkItemChildren(getSelectedItem(), communicator);
            createLabWork();
        });
    }

    private void labWorkSettings(LabWork labWork){
        cellBox.getChildren().addAll(labelForNumber, labelForName, addButton, deleteButton, editButton);
        labelForNumber.setText(labWork.getNumber().toString());
        labelForName.setText(labWork.getName());
        addButton.setOnAction(event -> {
            LabTreeView.checkItemChildren(getSelectedItem(), communicator);
            createTask();
        });
        deleteButton.setOnAction(event -> {
            deleteLabWork();
        });
        editButton.setOnAction(event -> {
            editLabWork();
        });
    }

    private void taskSettings(Task task){
        cellBox.getChildren().addAll(labelForNumber, labelForName, addButton, deleteButton, editButton);
        labelForNumber.setText(task.getNumber().toString());
        labelForName.setText(task.getName());
        addButton.setOnAction(event -> {
            LabTreeView.checkItemChildren(getSelectedItem(), communicator);
            createQuestion();
        });
        deleteButton.setOnAction(event -> {
            deleteTask();
        });
        editButton.setOnAction(event -> {
            editTask();
        });
    }

    private void questionSettings(Question question){
        cellBox.getChildren().addAll(labelForNumber, labelForName, deleteButton, editButton);
        labelForNumber.setText(question.getNumber().toString());
        labelForName.setText(question.getName());
        deleteButton.setOnAction(event -> {
            LabTreeView.checkItemChildren(getSelectedItem(), communicator);
            deleteQuestion();
        });
        editButton.setOnAction(event -> {
            editQuestion();
        });
    }

    private void noneSettings(){
    }


    private void createLabWork(){
        LabWork labWork = LabWork.builder().number(1).name("labWork_1").build();
        addItem.accept(new CustomTreeItem(new TreeDto(labWork)));
        try {
            communicator.saveLabWork(labWork);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void deleteLabWork(){
        LabWork labWork = deleteItem.get().getValue().getLabWork();
        try {
            communicator.deleteLabWorkTaskMatchingByLabWorkId(labWork.getId());
            communicator.deleteLabWork(labWork);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void editLabWork(){

    }



    private void createTask(){
        Task task = Task.builder().number(1).name("task_1").build();
        addItem.accept(new CustomTreeItem(new TreeDto(task)));
        try {
            communicator.saveTask(task);
            communicator.saveLabWorkTaskMatching(
                    LabWorkTask
                    .builder()
                    .labWork(getParentSelectedItem().getValue().getLabWork())
                    .task(task)
                    .number(0)
                    .build()
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void deleteTask(){
        Task task = deleteItem.get().getValue().getTask();
        try {
            List<LabWorkTask> labWorkTasks = communicator
                    .getLabWorkTaskMatching(getParentSelectedItem().getValue().getLabWork())
                    .stream()
                    .filter(labWorkTask -> labWorkTask.getTask().getId().equals(task.getId()))
                    .collect(Collectors.toList());
            for (LabWorkTask labWorkTask : labWorkTasks) {
                communicator.deleteLabWorkTaskMatching(labWorkTask);
            }
            communicator.deleteTaskQuestionMatchingByTaskId(task.getId());
            communicator.deleteTask(task);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void editTask(){

    }



    private void createQuestion(){
        Question question = Question.builder().number(1).name("question_1").build();
        addItem.accept(new CustomTreeItem(new TreeDto(question)));
        try {
            communicator.saveQuestion(question);
            communicator.saveTaskQuestionMatching(
                    TaskQuestion
                            .builder()
                            .task(getParentSelectedItem().getValue().getTask())
                            .question(question)
                            .number(0)
                            .build()
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void deleteQuestion(){
        Question question = deleteItem.get().getValue().getQuestion();
        try {
            List<TaskQuestion> taskQuestions = communicator
                    .getTaskQuestionMatching(getParentSelectedItem().getValue().getTask())
                    .stream()
                    .filter(taskQuestion -> taskQuestion.getQuestion().getId().equals(question.getId()))
                    .collect(Collectors.toList());
            for (TaskQuestion taskQuestion : taskQuestions) {
                communicator.deleteTaskQuestionMatching(taskQuestion);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void editQuestion(){

    }
}
