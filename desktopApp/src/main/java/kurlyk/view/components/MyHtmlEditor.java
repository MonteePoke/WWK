package kurlyk.view.components;

import javafx.scene.web.HTMLEditor;

public class MyHtmlEditor extends HTMLEditor {

    public MyHtmlEditor() {
        setMaxWidth(Double.MAX_VALUE);
        setPrefHeight(50);
        setMinHeight(200);
        lookupAll(".tool-bar").forEach(node -> {
            node.setVisible(false);
            node.setManaged(false);
        });
    }
}
