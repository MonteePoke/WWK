package kurlyk.view.components.labTreeView;

import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import kurlyk.QuestionType;
import kurlyk.WorkEntityType;
import kurlyk.communication.Communicator;
import kurlyk.models.LabWork;
import kurlyk.models.Question;
import kurlyk.models.Subject;
import kurlyk.models.Task;
import kurlyk.view.common.stage.StagePool;
import kurlyk.view.common.stage.Stages;
import kurlyk.view.create.createLtqWindow.CreateLtqStage;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
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
    private Button importButton;
    private CheckBox createTestTaskCheck;
    private Communicator communicator;
    private StagePool stagePool;
    private Consumer<CustomTreeItem> addItem;
    private Supplier<CustomTreeItem> deleteItem;

    public CustomTreeCell(
            Communicator communicator,
            StagePool stagePool,
            BiConsumer<CustomTreeItem, CustomTreeItem> addItem,
            Function<CustomTreeItem, CustomTreeItem> deleteItem) {
        this.communicator = communicator;
        this.stagePool = stagePool;
        this.addItem = (customTreeItem) -> addItem.accept(getSelectedItem(), customTreeItem);
        this.deleteItem = () -> deleteItem.apply(getSelectedItem());
    }

    private CustomTreeItem getSelectedItem() {
        return (CustomTreeItem) getTreeItem();
    }

    private CustomTreeItem getParentSelectedItem() {
        return getSelectedItem().getItemParent();
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
            importButton = new Button("Импорт");
            createTestTaskCheck = new CheckBox();
            createTestTaskCheck.setSelected(true);
            settings(item);
            setGraphic(cellBox);
            setText(null);
        }
    }

    private void settings(TreeDto treeDto) {
        addButton.setGraphic(ButtonPictures.ADD.getImageView());
        deleteButton.setGraphic(ButtonPictures.DELETE.getImageView());
        editButton.setGraphic(ButtonPictures.EDIT.getImageView());
        switch (treeDto.getType()) {
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

    private void subjectSettings(Subject subject) {
        cellBox.getChildren().addAll(labelForName, addButton);
        labelForName.setText(subject.getName());
        addButton.setOnAction(event -> {
            LabTreeView.checkItemChildren(getSelectedItem(), communicator);
            createLabWork();
        });
    }

    private void labWorkSettings(LabWork labWork) {
        cellBox.getChildren().addAll(labelForNumber, labelForName, addButton, deleteButton, editButton, createTestTaskCheck);
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
        createTestTaskCheck.selectedProperty().addListener((observable, oldValue, newValue) -> {
            try {
                LabTreeView.checkItemChildren(getSelectedItem(), communicator);
                Optional<TreeItem<TreeDto>> testItem = getSelectedItem().getChildren()
                        .stream()
                        .filter(treeItem -> treeItem.getValue().getTask().getNumber().equals(0))
                        .findAny();
                if(newValue && !testItem.isPresent()){
                    Task task = Task.builder().number(0).name("Тест").build();
                    Long taskId = communicator.saveTask(task);
                    task.setId(taskId);
                    getSelectedItem().getChildren().add(new CustomTreeItem(getSelectedItem(), new TreeDto(task)));
                }
                if(!newValue && testItem.isPresent()){
                    getSelectedItem().getChildren().remove(testItem.get());
                    communicator.deleteTask(testItem.get().getValue().getTask());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private void taskSettings(Task task) {
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

    private void questionSettings(Question question) {
        cellBox.getChildren().addAll(labelForNumber, labelForName, deleteButton, editButton, importButton);
        labelForNumber.setText(question.getNumber().toString());
        labelForName.setText(question.getName());
        deleteButton.setOnAction(event -> {
            LabTreeView.checkItemChildren(getSelectedItem(), communicator);
            deleteQuestion();
        });
        editButton.setOnAction(event -> {
            editQuestion();
        });
        importButton.setOnAction(event -> {

        });
    }

    private void noneSettings() {
    }


    private void createLabWork() {
        try {
            //labWork
            LabWork labWork = LabWork
                    .builder()
                    .number(getNumber())
                    .name("Лабораторная работа №" + getNumber())
                    .build();
            Long labWorkId = communicator.saveLabWork(labWork);
            labWork.setId(labWorkId);
            CustomTreeItem customTreeItem = new CustomTreeItem(getSelectedItem(), new TreeDto(labWork));
            //task
            Task task = Task
                    .builder()
                    .labWork(labWork)
                    .number(0)
                    .name("Тест")
                    .build();
            Long taskId = communicator.saveTask(task);
            task.setId(taskId);
            customTreeItem.getChildren().add(new CustomTreeItem(customTreeItem, new TreeDto(task)));
            addItem.accept(customTreeItem);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void deleteLabWork() {
        try {
            LabWork labWork = deleteItem.get().getValue().getLabWork();
            communicator.deleteLabWork(labWork);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void editLabWork() {
        Optional<LabWork> labWorkModel = Optional.of(getSelectedItem().getValue().getLabWork());
        stagePool.pushStageAndShowModal(Stages.LTQ_CREATE, new CreateLtqStage(
                WorkEntityType.LAB_WORK,
                labWorkModel,
                (LabWork labWork) -> {
                    try {
                        communicator.saveLabWork(labWork);
                        getSelectedItem().setValue(new TreeDto(labWork));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
        ));
    }


    private void createTask() {
        Task task = Task
                .builder()
                .labWork(getParentSelectedItem().getValue().getLabWork())
                .number(getNumber())
                .name("Задание №" + getNumber())
                .build();
        addItem.accept(new CustomTreeItem(getSelectedItem(), new TreeDto(task)));
        try {
            Long id = communicator.saveTask(task);
            task.setId(id);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void deleteTask() {
        try {
            Task task = deleteItem.get().getValue().getTask();
            communicator.deleteTask(task);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void editTask() {
        Optional<Task> taskModel = Optional.of(getSelectedItem().getValue().getTask());
        stagePool.pushStageAndShowModal(Stages.LTQ_CREATE, new CreateLtqStage(
                WorkEntityType.TASK,
                taskModel,
                (Task task) -> {
                    try {
                        communicator.saveTask(task);
                        getSelectedItem().setValue(new TreeDto(task));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
        ));
    }


    private void createQuestion() {
        Question question = Question
                .builder()
                .number(getNumber())
                .name("Вопрос №" + getNumber())
                .questionType(QuestionType.TEXT)
                .build();
        addItem.accept(new CustomTreeItem(getSelectedItem(), new TreeDto(question)));
        try {
            Long id = communicator.saveQuestion(question);
            question.setId(id);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void deleteQuestion() {
        try {
            Question question = deleteItem.get().getValue().getQuestion();
            communicator.deleteQuestion(question);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void editQuestion() {
        Optional<Question> questionModel = Optional.of(getSelectedItem().getValue().getQuestion());
        stagePool.pushStageAndShowModal(Stages.LTQ_CREATE, new CreateLtqStage(
                WorkEntityType.QUESTION,
                questionModel,
                (Question question) -> {
                    try {
                        communicator.saveQuestion(question);
                        getSelectedItem().setValue(new TreeDto(question));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
        ));
    }

    public int getNumber() {
        TreeDto parent = getSelectedItem().getValue();
        List<TreeDto> children = getSelectedItem().getChildren().stream().map(TreeItem::getValue).collect(Collectors.toList());
        switch (parent.getType()) {
            case SUBJECT:
                OptionalInt optionalNumberLabWork = children
                        .stream()
                        .mapToInt(treeDto -> treeDto.getLabWork().getNumber())
                        .max();
                if (optionalNumberLabWork.isPresent()) {
                    return optionalNumberLabWork.getAsInt() + 1;
                } else {
                    return 1;
                }
            case LAB_WORK:
                OptionalInt optionalNumberTask = children
                        .stream()
                        .mapToInt(treeDto -> treeDto.getTask().getNumber())
                        .max();
                if (optionalNumberTask.isPresent()) {
                    return optionalNumberTask.getAsInt() + 1;
                } else {
                    return 0;
                }
            case TASK:
                OptionalInt optionalNumberQuestion = children
                        .stream()
                        .mapToInt(treeDto -> treeDto.getQuestion().getNumber())
                        .max();
                if (optionalNumberQuestion.isPresent()) {
                    return optionalNumberQuestion.getAsInt() + 1;
                } else {
                    return 1;
                }
            case QUESTION:
            case NONE:
                break;
            default:
                throw new RuntimeException("Неизвестный тип элемента дерева");
        }
        return 1;
    }
}
