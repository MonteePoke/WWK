package kurlyk.view.create.createLtqWindow;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import kurlyk.common.classesMadeByStas.MyFunction;
import kurlyk.communication.Communicator;
import kurlyk.communication.UserInfo;
import kurlyk.models.LabWork;
import kurlyk.models.Question;
import kurlyk.models.Task;
import kurlyk.view.common.controller.Controller;
import kurlyk.view.common.stage.StagePool;
import kurlyk.view.components.DoubleField;
import kurlyk.view.components.IntegerField;
import kurlyk.view.components.table.StringCell;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.function.BiConsumer;

@Component
@Scope("prototype")
public class CreateLtqController extends Controller {

    @FXML private GridPane root;
    @FXML private Button submit;
    @FXML TableView<Question> questionTable;
    @FXML private TableColumn<Question, String> questionName;
    private Runnable closeStage;
    private int rowCounter;
    private Question selectedQuestion;

    //question handmade property
    private MyFunction<Integer> questionNumberProperty;
    private MyFunction<String> questionNameProperty;
    private MyFunction<Double> questionScoreProperty;
    private MyFunction<Integer> questionAttemptsNumberProperty;
    private MyFunction<String> questionDescriptionProperty;

    @Autowired
    private StagePool stagePool;

    @Autowired
    private UserInfo userInfo;

    @Autowired
    private Communicator communicator;


    public void initialize() {
        rowCounter = 0;
        root.setHgap(50);
        root.setVgap(20);
        root.setPadding(new Insets(15, 15, 15, 15));

        //table
        questionTable.getSelectionModel().selectedItemProperty().addListener((observableValue, oldVal, newVal) ->{
            selectedQuestion = newVal;
            questionNumberProperty.accept(newVal.getNumber());
            questionNameProperty.accept(newVal.getName());
            questionScoreProperty.accept(newVal.getScore());
            questionAttemptsNumberProperty.accept(newVal.getAtemptsNumber());
            questionDescriptionProperty.accept(newVal.getDescription());
        });

        try {
            questionTable.getItems().addAll(communicator.getQuestionHeaders());
        } catch (IOException e) {
            e.printStackTrace();
        }

        questionName.setCellValueFactory(new PropertyValueFactory<>("name"));
        questionName.setCellFactory(p -> new <Question>StringCell(this::commitChanges));
    }

    private void commitChanges(){
    }

    public void setCloseStage(Runnable closeStage) {
        this.closeStage = closeStage;
    }

    public void editLabWork(LabWork labWork, BiConsumer<LabWork, Integer> saveAction, Integer number) {
        MyFunction<Integer> numberProperty = createIntegerField("Номер", labWork.getNumber());
        MyFunction<String> nameProperty = createStringField("Название", labWork.getName());
        MyFunction<Integer> attemptsNumberProperty = createIntegerField("Количество попыток", labWork.getAtemptsNumber());

        submit.setOnAction(event -> {
            labWork.setNumber(numberProperty.get());
            labWork.setName(nameProperty.get());
            labWork.setAtemptsNumber(attemptsNumberProperty.get());
            saveAction.accept(labWork, null);
            closeStage.run();
        });
    }

    public void editTask(Task task, BiConsumer<Task, Integer> saveAction, Integer number) {
        MyFunction<Integer> numberProperty = createIntegerField("Номер", number);
        MyFunction<String> nameProperty = createStringField("Название", task.getName());
        MyFunction<Double> scoreProperty = createDoubleField("Количество баллов", task.getScore());
        MyFunction<Integer> attemptsNumberProperty = createIntegerField("Количество попыток", task.getAtemptsNumber());
        MyFunction<Boolean> isTestProperty = createBooleanField("Тестовое задание", task.getIsTest());

        submit.setOnAction(event -> {
            task.setNumber(numberProperty.get());
            task.setName(nameProperty.get());
            task.setScore(scoreProperty.get());
            task.setAtemptsNumber(attemptsNumberProperty.get());
            task.setIsTest(isTestProperty.get());
            saveAction.accept(task, numberProperty.get());
            closeStage.run();
        });
    }

    public void editQuestion(Question question, BiConsumer<Question, Integer> saveAction, Integer number) {
        selectedQuestion = question;
        questionNumberProperty = createIntegerField("Номер", number);
        questionNameProperty = createStringField("Название", question.getName(), false);
        questionScoreProperty = createDoubleField("Количество баллов", question.getScore(), false);
        questionAttemptsNumberProperty = createIntegerField("Количество попыток", question.getAtemptsNumber(), false);
        questionDescriptionProperty = createStringField("Описание", question.getDescription(), false);
        questionTable.setVisible(true);

        submit.setOnAction(event -> {
            question.setNumber(questionNumberProperty.get());
            saveAction.accept(selectedQuestion, questionNumberProperty.get());
            closeStage.run();
        });
    }



    private MyFunction<String> createStringField(String name, String value) {
        return createStringField(name, value, true);
    }

    private MyFunction<Integer> createIntegerField(String name, Integer value) {
        return createIntegerField(name, value, true);
    }

    private MyFunction<Double> createDoubleField(String name, Double value) {
        return createDoubleField(name, value, true);
    }

    private MyFunction<Boolean> createBooleanField(String name, Boolean value) {
        return createBooleanField(name, value, true);
    }

    private MyFunction<String> createStringField(String name, String value, boolean editable) {
        TextField field = new TextField(value);
        field.setEditable(editable);
        setRow(name, field);
        return new MyFunction<>(field::setText, field::getText);
    }

    private MyFunction<Integer> createIntegerField(String name, Integer value, boolean editable) {
        IntegerField field = new IntegerField(value);
        field.setEditable(editable);
        setRow(name, field);
        return new MyFunction<>(field::setNumber, field::getNumber);
    }

    private MyFunction<Double> createDoubleField(String name, Double value, boolean editable) {
        DoubleField field = new DoubleField(value);
        field.setEditable(editable);
        setRow(name, field);
        return new MyFunction<>(field::setNumber, field::getNumber);
    }

    private MyFunction<Boolean> createBooleanField(String name, Boolean value, boolean editable) {
        CheckBox field = new CheckBox();
        field.setSelected(value);
        field.setDisable(!editable);
        setRow(name, field);
        return new MyFunction<>(field::setSelected, field::isSelected);
    }

    private <T extends Region> void setRow(String name, T field) {
        field.setStyle("-fx-font-size: 16px;");

        Label label = createNameLabel(name);
        GridPane.setConstraints(label, 1, rowCounter);
        GridPane.setConstraints(field, 2, rowCounter);
        rowCounter++;
        root.getChildren().addAll(label, field);
    }

    private Label createNameLabel(String name) {
        Label label = new Label(name);
        label.setStyle("-fx-font-size: 16px;");
        label.setAlignment(Pos.BASELINE_RIGHT);
        return label;
    }
}