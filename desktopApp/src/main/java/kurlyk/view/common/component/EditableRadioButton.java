package kurlyk.view.common.component;

import javafx.geometry.Pos;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.web.HTMLEditor;
import lombok.Getter;

public class EditableRadioButton extends HBox {
    @Getter private RadioButton radioButton;
    @Getter private HTMLEditor htmlEditor;

    public EditableRadioButton(String text, boolean editable) {
        super();
        radioButton = new RadioButton();
        htmlEditor = new HTMLEditor();
        HBox.setHgrow(htmlEditor, Priority.SOMETIMES);
        htmlEditor.setMaxWidth(Double.MAX_VALUE);
        hideHTMLEditorToolbars(htmlEditor);
        htmlEditor.setHtmlText(text);
        htmlEditor.setDisable(!editable);
        htmlEditor.setPrefHeight(50);


        //Что бы дочерние элементы по центру были
        setAlignment(Pos.CENTER);
        getChildren().addAll(radioButton, htmlEditor);
    }

    private void hideHTMLEditorToolbars(final HTMLEditor editor) {
        editor.lookupAll(".tool-bar").forEach(node -> {
            node.setVisible(false);
            node.setManaged(false);
        });
    }
}
