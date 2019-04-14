package kurlyk.view.task.computerSystemDiagramWindow.characteristicWindow;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import kurlyk.view.common.component.DoubleField;
import kurlyk.view.common.controller.Controller;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class CharacteristicController extends Controller {

    @FXML private GridPane characteristicTable;
    private int rowCounter = 1;

    public void initialize(){
        ColumnConstraints column1 = new ColumnConstraints();
        column1.setPercentWidth(50);
        characteristicTable.getColumnConstraints().add(column1);
        ColumnConstraints column2 = new ColumnConstraints();
        column2.setPercentWidth(50);
        characteristicTable.getColumnConstraints().add(column2);
        characteristicTable.setGridLinesVisible(true);
    }

    public void addCharacteristic(String name, StringProperty property){
        Label keyLabel = new Label(name);
        setupPropertiesForKey(keyLabel);

        TextField valueField = new TextField(property.getValue());
        valueField.textProperty().addListener((observable, oldValue, newValue) -> {
            property.set(newValue);
        });
        setupPropertiesForValue(valueField);

        addRow(keyLabel, valueField);
    }

    public void addCharacteristic(String name, DoubleProperty property){
        Label keyLabel = new Label(name);
        setupPropertiesForKey(keyLabel);

        DoubleField valueField = new DoubleField(property.getValue());
        valueField.textProperty().addListener((observable, oldValue, newValue) -> {
            property.set(new Double(newValue));
        });
        setupPropertiesForValue(valueField);

        addRow(keyLabel, valueField);
    }

    private void addRow(Node... nodes){
        characteristicTable.getChildren().addAll(nodes);
        rowCounter++;
    }

    private void setupPropertiesForKey(Label label){
        GridPane.setConstraints(label, 0, rowCounter);
        GridPane.setHalignment(label, HPos.CENTER);
    }

    private void setupPropertiesForValue(TextField textField){
        GridPane.setConstraints(textField, 1, rowCounter);
        GridPane.setHalignment(textField, HPos.CENTER);
        textField.setAlignment(Pos.CENTER);
        textField.setFocusTraversable(false);
    }
}