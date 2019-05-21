package kurlyk.view.create.createLtqWindow;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import kurlyk.communication.Communicator;
import javafx.scene.layout.Region;
import kurlyk.communication.UserInfo;
import kurlyk.models.LabWork;
import kurlyk.models.Question;
import kurlyk.models.Task;
import kurlyk.view.common.controller.Controller;
import kurlyk.view.common.stage.StagePool;
import kurlyk.view.components.DoubleField;
import kurlyk.view.components.IntegerField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;
import java.util.function.Supplier;

@Component
@Scope("prototype")
public class CreateLtqController extends Controller {

    @FXML private GridPane root;
    @FXML private Button submit;
    private Runnable closeStage;

    @Autowired
    private StagePool stagePool;

    @Autowired
    private UserInfo userInfo;

    @Autowired
    private Communicator communicator;


    public void initialize(){
    }

    public void setCloseStage(Runnable closeStage) {
        this.closeStage = closeStage;
    }

    public void editLabWork(LabWork labWork, Consumer<LabWork> saveAction){
        Supplier<Integer> numberSupplier = createIntegerField("Номер", labWork.getNumber());
        Supplier<String> nameSupplier = createStringField("Название", labWork.getName());
        Supplier<Integer> atemptsNumberSupplier = createIntegerField("Количество попыток", labWork.getAtemptsNumber());

        submit.setOnAction(event -> {
            labWork.setNumber(numberSupplier.get());
            labWork.setName(nameSupplier.get());
            labWork.setAtemptsNumber(atemptsNumberSupplier.get());
            saveAction.accept(labWork);
            closeStage.run();
        });
    }

    public void editTask(Task task, Consumer<Task> saveAction){
        Supplier<Integer> numberSupplier = createIntegerField("Название", task.getNumber());
        Supplier<String> nameSupplier = createStringField("Номер", task.getName());
        Supplier<Double> scoreSupplier = createDoubleField("Количество баллов", task.getScore());
        Supplier<Integer> atemptsNumberSupplier = createIntegerField("Количество попыток", task.getAtemptsNumber());
        Supplier<Boolean> isTestSupplier = createBooleanField("Тестовое задание", task.getIsTest());

        submit.setOnAction(event -> {
            task.setNumber(numberSupplier.get());
            task.setName(nameSupplier.get());
            task.setScore(scoreSupplier.get());
            task.setAtemptsNumber(atemptsNumberSupplier.get());
            task.setIsTest(isTestSupplier.get());
            saveAction.accept(task);
            closeStage.run();
        });
    }

    public void editQuestion(Question question, Consumer<Question> saveAction){
        Supplier<Integer> numberSupplier = createIntegerField("Название", question.getNumber());
        Supplier<String> nameSupplier = createStringField("Номер", question.getName());
        Supplier<Double> scoreSupplier = createDoubleField("Количество баллов", question.getScore());
        Supplier<Integer> atemptsNumberSupplier = createIntegerField("Количество попыток", question.getAtemptsNumber());
        Supplier<String> descriptionSupplier = createStringField("Описание", question.getDescription());

        submit.setOnAction(event -> {
            question.setNumber(numberSupplier.get());
            question.setName(nameSupplier.get());
            question.setScore(scoreSupplier.get());
            question.setAtemptsNumber(atemptsNumberSupplier.get());
            question.setDescription(descriptionSupplier.get());
            saveAction.accept(question);
            closeStage.run();
        });
    }

    private Supplier<String> createStringField(String name, String value){
        TextField field = new TextField(value);
        setTextField(name, field);
        return field::getText;
    }

    private Supplier<Integer> createIntegerField(String name, Integer value){
        IntegerField field = new IntegerField(value);
        setTextField(name, field);
        return field::getNumber;
    }

    private Supplier<Double> createDoubleField(String name, Double value){
        DoubleField field = new DoubleField(value);
        setTextField(name, field);
        return field::getNumber;
    }

    private Supplier<Boolean> createBooleanField(String name, Boolean value){
        CheckBox field = new CheckBox();
        field.setSelected(value);
        setTextField(name, field);
        return field::isSelected;
    }

    private <T extends Region> void setTextField(String name, T field){
        HBox.setHgrow(field, Priority.ALWAYS);
        field.setMaxWidth(Double.MAX_VALUE);
        field.setStyle("-fx-font-size: 14px;");
        root.getChildren().add(new HBox(createNameLabel(name), field));
    }

    private Label createNameLabel(String name){
        Label label = new Label(name);
        HBox.setHgrow(label, Priority.ALWAYS);
        label.setMaxWidth(Double.MAX_VALUE);
        label.setPrefWidth(100);
        label.setStyle("-fx-font-size: 14px;");
        label.setAlignment(Pos.BASELINE_CENTER);
        return label;
    }
}