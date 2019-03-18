package kurlyk.view.common.component;

import javafx.geometry.Pos;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.web.HTMLEditor;
import lombok.Getter;

public class EditableCheckBox extends HBox {
    @Getter private CheckBox checkBox;
    @Getter private HTMLEditor htmlEditor;

    public EditableCheckBox(String text, boolean editable) {
        super();
        checkBox = new CheckBox();
        htmlEditor = new HTMLEditor();
        HBox.setHgrow(htmlEditor, Priority.SOMETIMES);
        htmlEditor.setMaxWidth(Double.MAX_VALUE);
        hideHTMLEditorToolbars(htmlEditor);
        htmlEditor.setHtmlText(text);
        htmlEditor.setDisable(!editable);
        htmlEditor.setPrefHeight(100);

        //Что бы дочерние элементы по центру были
        setAlignment(Pos.CENTER);
        getChildren().addAll(checkBox, htmlEditor);
    }

    private void hideHTMLEditorToolbars(final HTMLEditor editor) {
        editor.lookupAll(".tool-bar").forEach(node -> {
            node.setVisible(false);
            node.setManaged(false);
        });
    }
}
