package kurlyk.view.common.component;

import javafx.scene.web.HTMLEditor;

public class MyHTMLEditor extends HTMLEditor {

    public MyHTMLEditor() {
        setPrefHeight(200);
        setMaxWidth(Double.MAX_VALUE);
        setPrefHeight(50);
        lookupAll(".tool-bar").forEach(node -> {
            node.setVisible(false);
            node.setManaged(false);
        });
    }
}
