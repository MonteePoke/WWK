package kurlyk.view.components.labTreeView;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TreeCell;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import kurlyk.communication.Communicator;
import kurlyk.models.LabWork;
import kurlyk.models.Question;
import kurlyk.models.Subject;
import kurlyk.models.Task;

import java.util.function.Consumer;
import java.util.function.Supplier;

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
    private Supplier<CustomTreeItem> getItem;

    public CustomTreeCell(
            Communicator communicator,
            Consumer<CustomTreeItem> addItem,
            Supplier<CustomTreeItem> deleteItem,
            Supplier<CustomTreeItem> getItem) {
        this.communicator = communicator;
        this.addItem = addItem;
        this.deleteItem = deleteItem;
        this.getItem = getItem;
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
                    throw new RuntimeException("Неизвестный тип элемента для дерева");
        }
    }

    private void subjectSettings(Subject subject){
        cellBox.getChildren().addAll(labelForName, addButton);
        labelForName.setText(subject.getName());
        addButton.setOnAction(event -> {
            createLabWork();
        });
    }

    private void labWorkSettings(LabWork labWork){
        cellBox.getChildren().addAll(labelForNumber, labelForName, addButton, deleteButton, editButton);
        labelForNumber.setText(labWork.getNumber().toString());
        labelForName.setText(labWork.getName());
        addButton.setOnAction(event -> {
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
    }
    private void deleteLabWork(){
        LabWork labWork = deleteItem.get().getValue().getLabWork();
    }
    private void editLabWork(){

    }



    private void createTask(){
        Task task = Task.builder().number(1).name("task_1").build();
        addItem.accept(new CustomTreeItem(new TreeDto(task)));
    }
    private void deleteTask(){
        Task task = deleteItem.get().getValue().getTask();
    }
    private void editTask(){

    }



    private void createQuestion(){
        Question question = Question.builder().number(1).name("task_1").build();
        addItem.accept(new CustomTreeItem(new TreeDto(question)));
    }
    private void deleteQuestion(){
        Question question = deleteItem.get().getValue().getQuestion();
    }
    private void editQuestion(){

    }
}
