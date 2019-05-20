package kurlyk.view.create.createLabWorkWindow;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import kurlyk.communication.Communicator;
import kurlyk.models.LabWork;
import kurlyk.view.common.controller.Controller;
import kurlyk.view.common.stage.StagePool;
import kurlyk.view.common.stage.Stages;
import kurlyk.view.components.table.IntegerCell;
import kurlyk.view.components.table.StringCell;
import kurlyk.view.create.createLabWorkWindow.labWorkTaskMatchingWindow.LabWorkTaskMatchingStage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Scope("prototype")
public class CreateLabWorkController extends Controller {

    @FXML private TableView<LabWork> labWorkTable;
    @FXML private TableColumn<LabWork, LabWork> labWorkNumber;
    @FXML private TableColumn<LabWork, String> labWorkName;
    @FXML private TableColumn<LabWork, Integer> labWorkAtemptsNumber;

    @FXML private Button back;
    @FXML private Button createLabWork;
    @FXML private Button deleteLabWork;
    @FXML private Button addTask;
    private LabWork selectedLabWork;


    @Autowired
    private StagePool stagePool;

    @Autowired
    private Communicator communicator;


    public void initialize() {
        labWorkTable.setEditable(true);
        labWorkTable.getSelectionModel().selectedItemProperty().addListener((observableValue, oldVal, newVal) ->
                selectedLabWork = newVal
        );

        try {
            labWorkTable.getItems().addAll(communicator.getLabWorks());
        } catch (IOException e) {
            e.printStackTrace();
        }

        labWorkName.setCellValueFactory(new PropertyValueFactory<>("name"));
        labWorkName.setCellFactory(p -> new <LabWork>StringCell(this::commitChanges));
        labWorkName.setOnEditCommit(
                t -> t.getTableView().getItems().get(t.getTablePosition().getRow()).setName(t.getNewValue())
        );
        labWorkAtemptsNumber.setCellValueFactory(new PropertyValueFactory<>("atemptsNumber"));
        labWorkAtemptsNumber.setCellFactory(p -> new <LabWork>IntegerCell(this::commitChanges));
        labWorkAtemptsNumber.setOnEditCommit(
                t -> t.getTableView().getItems().get(t.getTablePosition().getRow()).setAtemptsNumber(t.getNewValue())
        );

        labWorkNumber.setCellValueFactory(p -> new ReadOnlyObjectWrapper<>(p.getValue()));
        labWorkNumber.setCellFactory(new Callback<TableColumn<LabWork, LabWork>, TableCell<LabWork, LabWork>>() {
            @Override public TableCell<LabWork, LabWork> call(TableColumn<LabWork, LabWork> param) {
                return new TableCell<LabWork, LabWork>() {
                    @Override protected void updateItem(LabWork item, boolean empty) {
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
        createLabWork.setOnAction(event -> {
            LabWork labWork = LabWork
                    .builder()
                    .name("Новая лабораторная работа")
                    .atemptsNumber(1)
                    .build();
            try {
                communicator.saveLabWork(labWork);
                labWorkTable.getItems().add(labWork);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        deleteLabWork.setOnAction(event -> {
            if (selectedLabWork != null) {
                try {
                    communicator.deleteLabWork(selectedLabWork);
                    labWorkTable.getItems().remove(selectedLabWork);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        addTask.setOnAction(event -> {
            if (selectedLabWork != null) {
                stagePool.pushStageAndShowModal(
                        Stages.LAB_WORK_TASK_MATCHING,
//                        stagePool.getStage(Stages.CREATE_LAB_WORK),
                        new LabWorkTaskMatchingStage(selectedLabWork)
                );
            }
        });
    }

    private void commitChanges(){
        try {
            communicator.saveLabWork(selectedLabWork);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}