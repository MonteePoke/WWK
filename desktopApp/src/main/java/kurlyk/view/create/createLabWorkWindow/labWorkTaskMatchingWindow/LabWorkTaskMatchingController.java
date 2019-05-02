package kurlyk.view.create.createLabWorkWindow.labWorkTaskMatchingWindow;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.util.Callback;
import kurlyk.communication.Communicator;
import kurlyk.models.LabWork;
import kurlyk.models.LabWorkTask;
import kurlyk.view.common.controller.Controller;
import kurlyk.view.common.stage.StagePool;
import kurlyk.view.common.stage.Stages;
import kurlyk.view.components.table.StringCell;
import kurlyk.view.create.createLabWorkWindow.taskListWindow.TaskListStage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Scope("prototype")
public class LabWorkTaskMatchingController extends Controller {

    @FXML private TableView<LabWorkTask> labWorkTaskMatchingTable;
    @FXML private TableColumn<LabWorkTask, LabWorkTask> taskNumber;
    @FXML private TableColumn<LabWorkTask, String> taskName;

    @FXML private Button addTask;
    @FXML private Button deleteTask;
    private LabWorkTask selectedLabWorkTask;


    @Autowired
    private StagePool stagePool;

    @Autowired
    private Communicator communicator;


    public void initialize() {
        labWorkTaskMatchingTable.getSelectionModel().selectedItemProperty().addListener((observableValue, oldVal, newVal) ->
                selectedLabWorkTask = newVal
        );

        taskName.setCellValueFactory(p -> {
            StringProperty stringProperty = new SimpleStringProperty();
            stringProperty.setValue(p.getValue().getTask().getName());
            return stringProperty;
        });
        taskName.setCellFactory(p -> new <LabWorkTask>StringCell(this::commitChanges));

        taskNumber.setCellValueFactory(p -> new ReadOnlyObjectWrapper<>(p.getValue()));
        taskNumber.setCellFactory(new Callback<TableColumn<LabWorkTask, LabWorkTask>, TableCell<LabWorkTask, LabWorkTask>>() {
            @Override public TableCell<LabWorkTask, LabWorkTask> call(TableColumn<LabWorkTask, LabWorkTask> param) {
                return new TableCell<LabWorkTask, LabWorkTask>() {
                    @Override protected void updateItem(LabWorkTask item, boolean empty) {
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


        deleteTask.setOnAction(event -> {
            if (selectedLabWorkTask != null) {
                try {
                    labWorkTaskMatchingTable.getItems().remove(selectedLabWorkTask);
                    communicator.deleteLabWorkTaskMatching(selectedLabWorkTask);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void setContent(LabWork labWork){
        try {
            labWorkTaskMatchingTable.getItems().addAll(communicator.getLabWorkTaskMatching(labWork));
        } catch (IOException e) {
            e.printStackTrace();
        }

        addTask.setOnAction(event -> {
            stagePool.pushStageAndShowModal(
                    Stages.TASK_LIST,
                    stagePool.getStage(Stages.LAB_WORK_TASK_MATCHING),
                    new TaskListStage((task -> {
                        try {
                            LabWorkTask labWorkTask = LabWorkTask
                                    .builder()
                                    .labWork(labWork)
                                    .task(task)
                                    .build();
                            communicator.saveLabWorkTaskMatching(labWorkTask);
                            labWorkTaskMatchingTable.getItems().add(labWorkTask);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }))
            );
        });
    }

    private void commitChanges(){
    }
}