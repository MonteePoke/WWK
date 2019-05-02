package kurlyk.view.create.createLabWorkWindow.taskListWindow;

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
import kurlyk.view.components.table.StringCell;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.function.Consumer;

@Component
@Scope("prototype")
public class TaskListController extends Controller {

    @FXML private TableView<Task> taskTable;
    @FXML private TableColumn<Task, Task> taskNumber;
    @FXML private TableColumn<Task, String> taskName;

    @FXML private Button ok;
    private Task selectedTask;


    @Autowired
    private StagePool stagePool;

    @Autowired
    private Communicator communicator;



    public void initialize() {
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
    }

    public void setOk(Consumer<Task> applySelection){
        if (selectedTask != null) {
            ok.setOnAction(event -> {
                applySelection.accept(selectedTask);
                stagePool.deleteStage(Stages.TASK_LIST);
            });
        }
    }

    private void commitChanges(){
    }
}