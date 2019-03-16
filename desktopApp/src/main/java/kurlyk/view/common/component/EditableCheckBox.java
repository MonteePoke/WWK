package kurlyk.view.common.component;

import javafx.geometry.Pos;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import lombok.Getter;

public class EditableCheckBox extends HBox {
    @Getter private CheckBox checkBox;
    @Getter private TextField textField;

    public EditableCheckBox(String text, boolean editable) {
        super();
        checkBox = new CheckBox();
        textField = new TextField(text);
        HBox.setHgrow(textField, Priority.SOMETIMES);
        textField.setMaxWidth(Double.MAX_VALUE);
        textField.setEditable(editable);

        //Что бы дочерние элементы по центру были
        setAlignment(Pos.CENTER);
        getChildren().addAll(checkBox, textField);
    }

    public EditableCheckBox(String text) {
        this(text, false);
    }
}
