package kurlyk.view.components;

import javafx.geometry.Pos;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.web.HTMLEditor;
import kurlyk.view.components.fields.IntegerField;
import kurlyk.view.components.fields.SignDeterminant;
import lombok.Getter;

public class EditableCheckBox extends HBox {
    @Getter private CheckBox checkBox;
    @Getter private HTMLEditor htmlEditor;
    @Getter private IntegerField coefficientField;

    public EditableCheckBox(Boolean isSelected, String text, Integer coefficient, boolean editable) {
        super();
        checkBox = new CheckBox();
        checkBox.setSelected(isSelected);

        htmlEditor = new HTMLEditor();
        HBox.setHgrow(htmlEditor, Priority.SOMETIMES);
        htmlEditor.setMaxWidth(Double.MAX_VALUE);
        hideHTMLEditorToolbars(htmlEditor);
        htmlEditor.setHtmlText(text);
        htmlEditor.setDisable(!editable);
        htmlEditor.setPrefHeight(50);

        coefficientField = new IntegerField(coefficient, SignDeterminant.POSITIVE_ONLY);

        //Что бы дочерние элементы по центру были
        setAlignment(Pos.CENTER);
        getChildren().addAll(checkBox, htmlEditor);
        if(editable){
            getChildren().add(coefficientField);
        }
    }

    private void hideHTMLEditorToolbars(final HTMLEditor editor) {
        editor.lookupAll(".tool-bar").forEach(node -> {
            node.setVisible(false);
            node.setManaged(false);
        });
    }
}
