package kurlyk.view.create.questionListWindow;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import kurlyk.communication.Communicator;
import kurlyk.transfer.QuestionForTableDto;
import kurlyk.view.common.controller.Controller;
import kurlyk.view.common.stage.StagePool;
import kurlyk.view.components.fields.IntegerField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.function.Consumer;

@Component
@Scope("prototype")
public class QuestionListController extends Controller {

    @FXML private TableView<QuestionForTableDto> questionTable;
    @FXML private TableColumn<QuestionForTableDto, Integer> questionNumber;
    @FXML private TableColumn<QuestionForTableDto, String> questionName;
    @FXML private TableColumn<QuestionForTableDto, Integer> taskNumber;
    @FXML private TableColumn<QuestionForTableDto, String> taskName;
    @FXML private TableColumn<QuestionForTableDto, Integer> labWorkNumber;
    @FXML private TableColumn<QuestionForTableDto, String> labWorkName;

    @FXML private IntegerField questionNumberField;
    @FXML private TextField questionNameField;
    @FXML private IntegerField taskNumberField;
    @FXML private TextField taskNameField;
    @FXML private IntegerField labWorkNumberField;
    @FXML private TextField labWorkNameField;

    @FXML private Button back;
    @FXML private Button search;
    @FXML private Button ok;

    private QuestionForTableDto selectedQuestionForTableDto;
    private Consumer<QuestionForTableDto> applySelection;
    private Runnable closeAction;


    @Autowired
    private StagePool stagePool;

    @Autowired
    private Communicator communicator;


    // TODO Паджинация
    public void initialize() {
        ok.setDisable(true);
        questionTable.getSelectionModel().selectedItemProperty().addListener((observableValue, oldVal, newVal) -> {
            selectedQuestionForTableDto = newVal;
            ok.setDisable(false);
        });

        try {
            questionTable.getItems().addAll(communicator.getQuestionsForTable(null, null));
        } catch (IOException e) {
            e.printStackTrace();
        }

        questionNumber.setCellValueFactory(new PropertyValueFactory<>("questionNumber"));
        questionName.setCellValueFactory(new PropertyValueFactory<>("questionName"));
        taskNumber.setCellValueFactory(new PropertyValueFactory<>("taskNumber"));
        taskName.setCellValueFactory(new PropertyValueFactory<>("taskName"));
        labWorkNumber.setCellValueFactory(new PropertyValueFactory<>("labWorkNumber"));
        labWorkName.setCellValueFactory(new PropertyValueFactory<>("labWorkName"));

        search.setOnAction(event -> {

        });

        back.setOnAction(event -> {
            closeAction.run();
        });

        ok.setOnAction(event -> {
            applySelection.accept(selectedQuestionForTableDto);
        });
    }

    public void setSelectAction(Consumer<QuestionForTableDto> applySelection) {
        this.applySelection = applySelection;
    }

    public void setCloseAction(Runnable closeAction) {
        this.closeAction = closeAction;
    }
}