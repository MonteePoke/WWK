package kurlyk.view.create.createLtqWindow;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import kurlyk.QuestionType;
import kurlyk.common.MyFunction;
import kurlyk.communication.Communicator;
import kurlyk.communication.UsverInfo;
import kurlyk.model.LabWork;
import kurlyk.model.Question;
import kurlyk.model.Task;
import kurlyk.view.common.controller.Controller;
import kurlyk.view.common.stage.StagePool;
import kurlyk.view.common.stage.Stages;
import kurlyk.view.components.CommonSceneCreator;
import kurlyk.view.components.fields.DoubleField;
import kurlyk.view.components.fields.IntegerField;
import kurlyk.view.components.fields.LongField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Objects;
import java.util.function.Consumer;

@Component
@Scope("prototype")
public class CreateLtqController extends Controller {

    @FXML private GridPane root;
    @FXML private Button submit;
    @FXML private Button editBodyQuestion;
    private Runnable closeStage;
    private int rowCounter;


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
        editBodyQuestion.setVisible(false);
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
        MyFunction<Boolean> negativeScoreProperty = createBooleanField("Отрицательный балл за задание", labWork.getNegativeScore());
        MyFunction<Long> decScoreProperty = createLongField("Вычитаемое значение при ошибке", labWork.getDecScore());
        submit.setOnAction(event -> {
            labWork.setNumber(numberProperty.get());
            labWork.setName(nameProperty.get());
            labWork.setAttemptsNumber(attemptsNumberProperty.get());
            labWork.setInterrupt(interruptProperty.get());
            labWork.setDefaultQuestionScore(defaultQuestionScoreProperty.get());
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
        MyFunction<Integer> questionNumberProperty = createIntegerField("Номер вопроса", question.getNumber());
        MyFunction<String> questionNameProperty = createStringField("Название вопроса", question.getName());
        MyFunction<QuestionType> questionTypeProperty = createQuestionTypeField("Тип вопроса", question.getQuestionType(), false);
        MyFunction<Long> questionScoreProperty = createLongField("Максимальный балл", question.getScore());
        MyFunction<Integer> questionAttemptsNumberProperty = createIntegerField("Количество попыток", question.getAttemptsNumber());
        editBodyQuestion.setVisible(true);
        editBodyQuestion.setOnAction(event -> {
            stagePool.setSceneStage(Stages.LTQ_CREATE, CommonSceneCreator.questionSceneCreator(
                    Objects.requireNonNull(getEditableQuestion(question.getId())),
                    true,
                    questionBeforeAction -> {},
                    questionAfterAction -> {},
                    Stages.LTQ_CREATE
            ));
        });

        submit.setOnAction(event -> {
            question.setNumber(questionNumberProperty.get());
            question.setName(questionNameProperty.get());
            question.setScore(questionScoreProperty.get());
            question.setQuestionType(questionTypeProperty.get());
            question.setAttemptsNumber(questionAttemptsNumberProperty.get());
            saveAction.accept(question);
            closeStage.run();
        });
    }

    private Question getEditableQuestion(Long questionId){
        try {
            return communicator.getQuestion(questionId);
        } catch (IOException e) {
            return null;
        }
    }

    private MyFunction<QuestionType> createQuestionTypeField(String name, QuestionType value) {
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

    private MyFunction<QuestionType> createQuestionTypeField(String name, QuestionType value, boolean editable) {
        ComboBox<QuestionType> field = new ComboBox<>();
        field.getItems().addAll(
                QuestionType.COMPUTER_SYSTEM,
                QuestionType.FORMULA,
                QuestionType.TEXT,
                QuestionType.NUMBER,
                QuestionType.MATCHING,
                QuestionType.CHECK,
                QuestionType.RADIO
        );
        field.setDisable(!editable);
        field.setValue(value);
        setRow(name, field);
        return new MyFunction<>(field::setValue, field::getValue);
    }

    private MyFunction<Integer> createIntegerField(String name, Integer value, boolean editable) {
        IntegerField field = new IntegerField(value);
        field.setEditable(editable);
        setRow(name, field);
        return new MyFunction<>(field::setValue, field::getValue);
    }

    private MyFunction<Long> createLongField(String name, Long value, boolean editable) {
        LongField field = new LongField(value);
        field.setEditable(editable);
        setRow(name, field);
        return new MyFunction<>(field::setValue, field::getValue);
    }

    private MyFunction<Double> createDoubleField(String name, Double value, boolean editable) {
        DoubleField field = new DoubleField(value);
        field.setEditable(editable);
        setRow(name, field);
        return new MyFunction<>(field::setValue, field::getValue);
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