package kurlyk.view.create.createTaskWindow;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import kurlyk.communication.Communicator;
import kurlyk.models.Task;
import kurlyk.view.common.controller.Controller;
import kurlyk.view.common.stage.StagePool;
import kurlyk.view.common.stage.Stages;
import kurlyk.view.components.table.BooleanCell;
import kurlyk.view.components.table.DoubleCell;
import kurlyk.view.components.table.StringCell;
import kurlyk.view.create.createTaskWindow.taskQuestionMatchingWindow.TaskQuestionMatchingStage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Scope("prototype")
public class CreateTaskController extends Controller {

    @FXML private TableView<Task> taskTable;
    @FXML private TableColumn<Task, Task> taskNumber;
    @FXML private TableColumn<Task, String> taskName;
    @FXML private TableColumn<Task, Double> taskScore;
    @FXML private TableColumn<Task, Boolean> taskIsTest;

    @FXML private Button back;
    @FXML private Button createTask;
    @FXML private Button deleteTask;
    @FXML private Button addQuestion;
    private Task selectedTask;


    @Autowired
    private StagePool stagePool;

    @Autowired
    private Communicator communicator;


    public void initialize() {
        taskTable.setEditable(true);
        taskTable.getSelectionModel().selectedItemProperty().addListener((observableValue, oldVal, newVal) ->
            selectedTask = newVal
        );

        try {
            taskTable.getItems().addAll(communicator.getTasks());
        } catch (IOException e) {
            e.printStackTrace();
        }

        taskName.setCellValueFactory(new PropertyValueFactory<>("name"));
        taskName.setCellFactory(p -> new <Task>StringCell(this::commitChanges));
        taskName.setOnEditCommit(
                t -> t.getTableView().getItems().get(t.getTablePosition().getRow()).setName(t.getNewValue())
        );
        taskScore.setCellValueFactory(new PropertyValueFactory<>("score"));
        taskScore.setCellFactory(p -> new <Task>DoubleCell(this::commitChanges));
        taskScore.setOnEditCommit(
                t -> t.getTableView().getItems().get(t.getTablePosition().getRow()).setScore(t.getNewValue())
        );
        taskIsTest.setCellValueFactory(new PropertyValueFactory<>("isTest"));
        taskIsTest.setCellFactory(p -> new <Task>BooleanCell(this::commitChanges));
        taskIsTest.setOnEditCommit(
                t -> t.getTableView().getItems().get(t.getTablePosition().getRow()).setIsTest(t.getNewValue())
        );

        taskNumber.setCellValueFactory(p -> new ReadOnlyObjectWrapper<>(p.getValue()));
        taskNumber.setCellFactory(new Callback<TableColumn<Task, Task>, TableCell<Task, Task>>() {
            @Override public TableCell<Task, Task> call(TableColumn<Task, Task> param) {
                return new TableCell<Task, Task>() {
                    @Override protected void updateItem(Task item, boolean empty) {
                        super.updateItem(item, empty);
                        if (this.getTableRow() != null && item != null) {
                            setText(this.getTableRow().getIndex()+"");
                        } else {
                            setText("");
                        }
                    }
                };
            }
        });

        back.setOnAction(event -> {
            stagePool.deleteStage(Stages.CREATE_TASK);
            stagePool.showStage(Stages.COMMON_CREATE);
        });
        createTask.setOnAction(event -> {
            Task task = Task.builder()
                    .name("Новая задача")
                    .score(1d)
                    .isTest(false)
                    .build();
            try {
                communicator.saveTask(task);
                taskTable.getItems().add(task);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        deleteTask.setOnAction(event -> {
            if (selectedTask != null) {
                try {
                    communicator.deleteTask(selectedTask);
                    taskTable.getItems().remove(selectedTask);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        addQuestion.setOnAction(event -> {
            if (selectedTask != null) {
                stagePool.pushStageAndShowModal(
                        Stages.TASK_QUESTION_MATCHING,
//                        stagePool.getStage(Stages.CREATE_TASK),
                        new TaskQuestionMatchingStage(selectedTask)
                );
            }
        });
    }

    private void commitChanges(){
        try {
            communicator.saveTask(selectedTask);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}