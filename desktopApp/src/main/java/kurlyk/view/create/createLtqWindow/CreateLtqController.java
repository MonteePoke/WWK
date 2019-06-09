package kurlyk.view.create.createLtqWindow;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import kurlyk.QuestionType;
import kurlyk.WhenShowAnswer;
import kurlyk.common.Codable;
import kurlyk.common.MyFunction;
import kurlyk.communication.Communicator;
import kurlyk.communication.UsverInfo;
import kurlyk.models.LabWork;
import kurlyk.models.Question;
import kurlyk.models.Task;
import kurlyk.view.common.controller.Controller;
import kurlyk.view.common.stage.StagePool;
import kurlyk.view.components.DoubleField;
import kurlyk.view.components.IntegerField;
import kurlyk.view.components.table.StringCell;
import kurlyk.view.components.toolbar.LongField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.function.Consumer;

@Component
@Scope("prototype")
public class CreateLtqController extends Controller {

    @FXML
    private GridPane root;
    @FXML
    private Button submit;
    @FXML
    TableView<Question> questionTable;
    @FXML
    private TableColumn<Question, String> questionName;
    private Runnable closeStage;
    private int rowCounter;
    private Question selectedQuestion;

    //question handmade property
    private MyFunction<Integer> questionNumberProperty;
    private MyFunction<String> questionNameProperty;
    private MyFunction<Long> questionScoreProperty;
    private MyFunction<Integer> questionAttemptsNumberProperty;
    private MyFunction<String> questionDescriptionProperty;
    private MyFunction<String> questionTypeProperty;

    @Autowired
    private StagePool stagePool;

    @Autowired
    private UsverInfo usverInfo;

    @Autowired
    private Communicator communicator;


    public void initialize() {
        rowCounter = 0;
        root.setHgap(50);
        root.setVgap(20);
        root.setPadding(new Insets(15, 15, 15, 15));

        //table
        questionTable.getSelectionModel().selectedItemProperty().addListener((observableValue, oldVal, newVal) -> {
            selectedQuestion = newVal;
            questionNumberProperty.accept(newVal.getNumber());
            questionNameProperty.accept(newVal.getName());
            questionScoreProperty.accept(newVal.getScore());
            questionAttemptsNumberProperty.accept(newVal.getAttemptsNumber());
        });

        try {
            questionTable.getItems().addAll(communicator.getQuestionHeaders());
        } catch (IOException e) {
            e.printStackTrace();
        }

        questionName.setCellValueFactory(new PropertyValueFactory<>("name"));
        questionName.setCellFactory(p -> new <Question>StringCell(this::commitChanges));
    }

    private void commitChanges() {
    }

    public void setCloseStage(Runnable closeStage) {
        this.closeStage = closeStage;
    }

    public void editLabWork(LabWork labWork, Consumer<LabWork> saveAction) {
        MyFunction<Integer> numberProperty = createIntegerField("Номер лабораторной работы", labWork.getNumber());
        MyFunction<String> nameProperty = createStringField("Название", labWork.getName());
        MyFunction<Integer> attemptsNumberProperty = createIntegerField("Количество попыток на ответа (по умолч.)", labWork.getAttemptsNumber());
        MyFunction<Boolean> interruptProperty = createBooleanField("Прерывать лабораторную", labWork.getInterrupt());
        MyFunction<Long> defaultQuestionScoreProperty = createLongField("Максимальный балл за вопрос (по умолч.)", labWork.getDefaultQuestionScore());
        MyFunction<String> whenShowAnswerProperty = createWhenShowAnswerField("Когда отображать ответ", labWork.getWhenShowAnswer());
        MyFunction<Boolean> negativeScoreProperty = createBooleanField("Отрицательный балл за задание", labWork.getNegativeScore());
        MyFunction<Long> decScoreProperty = createLongField("Вычитаемое значение при ошибке", labWork.getDecScore());
        submit.setOnAction(event -> {
            labWork.setNumber(numberProperty.get());
            labWork.setName(nameProperty.get());
            labWork.setAttemptsNumber(attemptsNumberProperty.get());
            labWork.setInterrupt(interruptProperty.get());
            labWork.setDefaultQuestionScore(defaultQuestionScoreProperty.get());
            labWork.setWhenShowAnswer(Codable.find(WhenShowAnswer.class, whenShowAnswerProperty.get()));
            labWork.setNegativeScore(negativeScoreProperty.get());
            labWork.setDecScore(decScoreProperty.get());
            saveAction.accept(labWork);
            closeStage.run();
        });
    }

    public void editTask(Task task, Consumer<Task> saveAction) {
        MyFunction<Integer> numberProperty = createIntegerField("Номер задания", task.getNumber());
        MyFunction<String> nameProperty = createStringField("Название", task.getName());
        MyFunction<Double> scoreProperty = createDoubleField("Балл заданий", task.getScoreMultiplier());

        submit.setOnAction(event -> {
            task.setNumber(numberProperty.get());
            task.setName(nameProperty.get());
            task.setScoreMultiplier(scoreProperty.get());
            saveAction.accept(task);
            closeStage.run();
        });
    }

    public void editQuestion(Question question, Consumer<Question> saveAction) {
        selectedQuestion = question;
        questionNumberProperty = createIntegerField("Номер вопроса", question.getNumber());
        questionNameProperty = createStringField("Название вопроса", question.getName());
        questionTypeProperty = createQuestionTypeField("Тип вопроса", question.getQuestionType());
        questionScoreProperty = createLongField("Максимальный балл", question.getScore(), true);
        questionAttemptsNumberProperty = createIntegerField("Количество попыток", question.getAttemptsNumber(), true);
        questionTable.setVisible(true);

        submit.setOnAction(event -> {
            question.setNumber(questionNumberProperty.get());
            question.setName(questionNameProperty.get());
            question.setScore(questionScoreProperty.get());
            question.setQuestionType(questionTypeProperty.get() != null ?
                    Codable.find(QuestionType.class, questionTypeProperty.get()) : null);
            question.setAttemptsNumber(questionAttemptsNumberProperty.get());
            saveAction.accept(selectedQuestion);
            closeStage.run();
        });
    }

    private MyFunction<String> createWhenShowAnswerField(String name, WhenShowAnswer value) {
        return createWhenShowAnswerField(name, value, true);
    }

    private MyFunction<String> createQuestionTypeField(String name, QuestionType value) {
        return createQuestionTypeField(name, value, true);
    }

    private MyFunction<String> createStringField(String name, String value) {
        return createStringField(name, value, true);
    }

    private MyFunction<Integer> createIntegerField(String name, Integer value) {
        return createIntegerField(name, value, true);
    }

    private MyFunction<Long> createLongField(String name, Long value) {
        return createLongField(name, value, true);
    }

    private MyFunction<Double> createDoubleField(String name, Double value) {
        return createDoubleField(name, value, true);
    }

    private MyFunction<Boolean> createBooleanField(String name, Boolean value) {
        return createBooleanField(name, value, true);
    }

    private MyFunction<String> createStringField(String name, String value, boolean editable) {
        TextField field = new TextField(value != null ? value : "");
        field.setEditable(editable);
        setRow(name, field);
        return new MyFunction<>(field::setText, field::getText);
    }

    private MyFunction<String> createWhenShowAnswerField(String name, WhenShowAnswer value, boolean editable) {
        ComboBox<String> field = new ComboBox<>();
        field.getItems().addAll(
                WhenShowAnswer.NEVER.getCode(),
                WhenShowAnswer.AFTER_FIRST_MISTAKE.getCode(),
                WhenShowAnswer.AFTER_THIRD_MISTAKE.getCode(),
                WhenShowAnswer.ALWAYS.getCode()
        );
        field.setEditable(editable);
        field.setValue(value != null ? value.getCode() : "");
        setRow(name, field);
        return new MyFunction<>(field::setValue, field::getValue);
    }


    private MyFunction<String> createQuestionTypeField(String name, QuestionType value, boolean editable) {
        ComboBox<String> field = new ComboBox<>();
        field.getItems().addAll(
                QuestionType.COMPUTER_SYSTEM.getCode(),
                QuestionType.FORMULA.getCode(),
                QuestionType.TEXT.getCode(),
                QuestionType.NUMBER.getCode(),
                QuestionType.MATCHING.getCode(),
                QuestionType.CHECK.getCode(),
                QuestionType.RADIO.getCode()
        );
        field.setEditable(editable);
        field.setValue(value != null ? value.getCode() : "");
        setRow(name, field);
        return new MyFunction<>(field::setValue, field::getValue);
    }

    private MyFunction<Integer> createIntegerField(String name, Integer value, boolean editable) {
        IntegerField field = new IntegerField(value);
        field.setEditable(editable);
        setRow(name, field);
        return new MyFunction<>(field::setNumber, field::getNumber);
    }

    private MyFunction<Long> createLongField(String name, Long value, boolean editable) {
        LongField field = new LongField(value);
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
        field.setSelected(value != null ? value : false);
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