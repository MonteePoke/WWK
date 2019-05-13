package kurlyk.view.create.createTaskWindow.questionListWindow;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import kurlyk.communication.Communicator;
import kurlyk.models.Question;
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
public class QuestionListController extends Controller {

    @FXML private TableView<Question> questionTable;
    @FXML private TableColumn<Question, Question> questionNumber;
    @FXML private TableColumn<Question, String> questionName;

    @FXML private Button ok;
    private Question selectedQuestion;


    @Autowired
    private StagePool stagePool;

    @Autowired
    private Communicator communicator;



    public void initialize() {
        questionTable.getSelectionModel().selectedItemProperty().addListener((observableValue, oldVal, newVal) ->
                selectedQuestion = newVal
        );

        try {
            questionTable.getItems().addAll(communicator.getQuestionHeaders());
        } catch (IOException e) {
            e.printStackTrace();
        }

        questionName.setCellValueFactory(new PropertyValueFactory<>("name"));
        questionName.setCellFactory(p -> new <Question>StringCell(this::commitChanges));

        questionNumber.setCellValueFactory(p -> new ReadOnlyObjectWrapper<>(p.getValue()));
        questionNumber.setCellFactory(new Callback<TableColumn<Question, Question>, TableCell<Question, Question>>() {
            @Override public TableCell<Question, Question> call(TableColumn<Question, Question> param) {
                return new TableCell<Question, Question>() {
                    @Override protected void updateItem(Question item, boolean empty) {
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

    public void setOk(Consumer<Question> applySelection){
        if (selectedQuestion != null) {
            ok.setOnAction(event -> {
                applySelection.accept(selectedQuestion);
                stagePool.deleteStage(Stages.QUESTION_LIST);
            });
        }
    }

    private void commitChanges(){
    }
}