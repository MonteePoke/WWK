package kurlyk.view.components.labTreeView;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import kurlyk.QuestionType;
import kurlyk.WorkEntityType;
import kurlyk.communication.Communicator;
import kurlyk.models.*;
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

    private CustomTreeItem getSelectedItem(){
        return (CustomTreeItem) getTreeItem();
    }

    private CustomTreeItem getParentSelectedItem(){
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
        try {
            //labWork
            LabWork labWork = LabWork.builder().number(getNumber()).name("labWork_1").build();
            Long id = communicator.saveLabWork(labWork);
            labWork.setId(id);
            CustomTreeItem customTreeItem = new CustomTreeItem(getSelectedItem(), new TreeDto(labWork));
            //task
            Task task = Task.builder().number(0).name("Текст").build();
            customTreeItem.getChildren().add(new CustomTreeItem(customTreeItem, new TreeDto(task)));
            addItem.accept(customTreeItem);
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
        Optional<LabWork> labWorkModel = Optional.of(getSelectedItem().getValue().getLabWork());
        stagePool.pushStageAndShowModal(Stages.LTQ_CREATE, new CreateLtqStage(
                WorkEntityType.LAB_WORK,
                labWorkModel,
                null,
                (LabWork labWork, Integer number) -> {
                    try {
                        communicator.saveLabWork(labWork);
                        getSelectedItem().setValue(new TreeDto(labWork));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
        ));
    }



    private void createTask(){
        Task task = Task.builder().number(getNumber()).name("task_1").build();
        addItem.accept(new CustomTreeItem(getSelectedItem(), new TreeDto(task)));
        try {
            Long id = communicator.saveTask(task);
            task.setId(id);
            communicator.saveLabWorkTaskMatching(
                    LabWorkTask
                    .builder()
                    .labWork(getSelectedItem().getValue().getLabWork())
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
        Optional<Task> taskModel = Optional.of(getSelectedItem().getValue().getTask());
        Integer labWorkTaskNumber = 0;
        try {
            labWorkTaskNumber = communicator.getLabWorkTaskMatching(getParentSelectedItem().getValue().getLabWork())
                    .stream()
                    .filter(lwt -> lwt.getTask().getId().equals(taskModel.get().getId()))
                    .map(LabWorkTask::getNumber)
                    .findAny()
                    .get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        stagePool.pushStageAndShowModal(Stages.LTQ_CREATE, new CreateLtqStage(
                WorkEntityType.TASK,
                taskModel,
                labWorkTaskNumber,
                (Task task, Integer number) -> {
                    try {
                        communicator.saveTask(task);
                        Optional<LabWorkTask> optionalLabWorkTask = communicator
                                .getLabWorkTaskMatching(getParentSelectedItem().getValue().getLabWork())
                                .stream()
                                .filter(lwt -> lwt.getTask().getId().equals(taskModel.get().getId()))
                                .findFirst();
                        communicator.saveLabWorkTaskMatching(
                                LabWorkTask
                                        .builder()
                                        .number(number)
                                        .id(optionalLabWorkTask.get().getId())
                                        .labWork(getParentSelectedItem().getValue().getLabWork())
                                        .task(task)
                                        .build()
                        );
                        getSelectedItem().setValue(new TreeDto(task));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
        ));
    }


    private void createQuestion(){
        Question question = Question.builder().number(getNumber()).name("question_1").questionType(QuestionType.TEXT).build();
        addItem.accept(new CustomTreeItem(getSelectedItem(), new TreeDto(question)));
        try {
            Long id = communicator.saveQuestion(question);
            question.setId(id);
            communicator.saveTaskQuestionMatching(
                    TaskQuestion
                            .builder()
                            .task(getSelectedItem().getValue().getTask())
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
        Optional<Question> questionModel = Optional.of(getSelectedItem().getValue().getQuestion());
        Integer taskQuestionNumber = 0;
        try {
            taskQuestionNumber = communicator.getTaskQuestionMatching(getParentSelectedItem().getValue().getTask())
                    .stream()
                    .filter(tq -> tq.getQuestion().getId().equals(questionModel.get().getId()))
                    .map(TaskQuestion::getNumber)
                    .findAny()
                    .get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        stagePool.pushStageAndShowModal(Stages.LTQ_CREATE, new CreateLtqStage(
                WorkEntityType.QUESTION,
                questionModel,
                taskQuestionNumber,
                (Question question, Integer number) -> {
                    try {
                        Optional<TaskQuestion> optionalTaskQuestion = communicator
                                .getTaskQuestionMatching(getParentSelectedItem().getValue().getTask())
                                .stream()
                                .filter(taskQuestion -> taskQuestion.getQuestion().getId().equals(questionModel.get().getId()))
                                .findFirst();
                        communicator.saveTaskQuestionMatching(
                                TaskQuestion
                                .builder()
                                .number(number)
                                .id(optionalTaskQuestion.get().getId())
                                .task(getParentSelectedItem().getValue().getTask())
                                .question(question)
                                .build()
                        );
                        getSelectedItem().setValue(new TreeDto(question));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
        ));
    }

    public int getNumber(){
        TreeDto parent = getSelectedItem().getValue();
        List<TreeDto> children = getSelectedItem().getChildren().stream().map(TreeItem::getValue).collect(Collectors.toList());
        switch (parent.getType()){
            case SUBJECT:
                OptionalInt optionalNumberLabWork = children
                        .stream()
                        .mapToInt(treeDto -> treeDto.getLabWork().getNumber())
                        .max();
                if(optionalNumberLabWork.isPresent()){
                    return optionalNumberLabWork.getAsInt() + 1;
                } else {
                    return 0;
                }
            case LAB_WORK:
                OptionalInt optionalNumberTask = children
                        .stream()
                        .mapToInt(treeDto -> treeDto.getTask().getNumber())
                        .max();
                if(optionalNumberTask.isPresent()){
                    return optionalNumberTask.getAsInt() + 1;
                } else {
                    return 0;
                }
            case TASK:
                OptionalInt optionalNumberQuestion = children
                        .stream()
                        .mapToInt(treeDto -> treeDto.getQuestion().getNumber())
                        .max();
                if(optionalNumberQuestion.isPresent()){
                    return optionalNumberQuestion.getAsInt() + 1;
                } else {
                    return 0;
                }
            case QUESTION:
                break;
            case NONE:
                break;
            default:
                throw new RuntimeException("Неизвестный тип элемента дерева");
        }
        return 1;
    }
}
