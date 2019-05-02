package kurlyk.view.create.createTaskWindow.taskQuestionMatchingWindow;

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
import kurlyk.models.Task;
import kurlyk.models.TaskQuestion;
import kurlyk.view.common.controller.Controller;
import kurlyk.view.common.stage.StagePool;
import kurlyk.view.common.stage.Stages;
import kurlyk.view.components.table.StringCell;
import kurlyk.view.create.createTaskWindow.questionListWindow.QuestionListStage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Scope("prototype")
public class TaskQuestionMatchingController extends Controller {

    @FXML private TableView<TaskQuestion> taskQuestionMatchingTable;
    @FXML private TableColumn<TaskQuestion, TaskQuestion> questionNumber;
    @FXML private TableColumn<TaskQuestion, String> questionName;

    @FXML private Button addQuestion;
    @FXML private Button deleteQuestion;
    private TaskQuestion selectedTaskQuestion;


    @Autowired
    private StagePool stagePool;

    @Autowired
    private Communicator communicator;


    public void initialize() {
        taskQuestionMatchingTable.getSelectionModel().selectedItemProperty().addListener((observableValue, oldVal, newVal) ->
                selectedTaskQuestion = newVal
        );

        questionName.setCellValueFactory(p -> {
            StringProperty stringProperty = new SimpleStringProperty();
            stringProperty.setValue(p.getValue().getQuestion().getName());
            return stringProperty;
        });
        questionName.setCellFactory(p -> new <TaskQuestion>StringCell(this::commitChanges));

        questionNumber.setCellValueFactory(p -> new ReadOnlyObjectWrapper<>(p.getValue()));
        questionNumber.setCellFactory(new Callback<TableColumn<TaskQuestion, TaskQuestion>, TableCell<TaskQuestion, TaskQuestion>>() {
            @Override public TableCell<TaskQuestion, TaskQuestion> call(TableColumn<TaskQuestion, TaskQuestion> param) {
                return new TableCell<TaskQuestion, TaskQuestion>() {
                    @Override protected void updateItem(TaskQuestion item, boolean empty) {
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


        deleteQuestion.setOnAction(event -> {
            if (selectedTaskQuestion != null) {
                try {
                    taskQuestionMatchingTable.getItems().remove(selectedTaskQuestion);
                    communicator.deleteTaskQuestionMatching(selectedTaskQuestion);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void setContent(Task task){
        try {
            taskQuestionMatchingTable.getItems().addAll(communicator.getTaskQuestionMatching(task));
        } catch (IOException e) {
            e.printStackTrace();
        }

        addQuestion.setOnAction(event -> {
            stagePool.pushStageAndShowModal(
                    Stages.QUESTION_LIST,
                    stagePool.getStage(Stages.TASK_QUESTION_MATCHING),
                    new QuestionListStage((question -> {
                        try {
                            TaskQuestion taskQuestion = TaskQuestion
                                    .builder()
                                    .task(task)
                                    .question(question)
                                    .build();
                            communicator.saveTaskQuestionMatching(taskQuestion);
                            taskQuestionMatchingTable.getItems().add(taskQuestion);
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