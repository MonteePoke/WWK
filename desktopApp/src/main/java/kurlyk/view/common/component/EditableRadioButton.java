package kurlyk.view.common.component;

import javafx.geometry.Pos;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import lombok.Getter;

public class EditableRadioButton extends HBox {
    @Getter private RadioButton radioButton;
    @Getter private TextField textField;

    public EditableRadioButton(String text, boolean editable) {
        super();
        radioButton = new RadioButton();
        textField = new TextField(text);
        HBox.setHgrow(textField, Priority.SOMETIMES);
        textField.setMaxWidth(Double.MAX_VALUE);
        textField.setEditable(editable);

        //Что бы дочерние элементы по центру были
        setAlignment(Pos.CENTER);
        getChildren().addAll(radioButton, textField);
    }

    public EditableRadioButton(String text) {
        this(text, false);
    }
}
