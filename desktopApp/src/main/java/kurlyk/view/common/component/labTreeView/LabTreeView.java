package kurlyk.view.common.component.labTreeView;

import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

public class LabTreeView extends TreeView {

    public LabTreeView() {
        BookCategory catJava = new BookCategory("JAVA-00", "Java");
        BookCategory catJSP = new BookCategory("JAVA-01", "Jsp");
        BookCategory catSpring = new BookCategory("JAVA-02", "Spring");

        // Root Item
        TreeItem<BookCategory> rootItem = new TreeItem<BookCategory>(catJava);
        rootItem.setExpanded(true);

        // JSP Item
        TreeItem<BookCategory> itemJSP = new TreeItem<BookCategory>(catJSP);

        // Spring Item
        TreeItem<BookCategory> itemSpring = new TreeItem<>(catSpring);

        // Add to Root
        rootItem.getChildren().addAll(itemJSP, itemSpring);

        TreeView<BookCategory> tree = new TreeView<BookCategory>(rootItem);
    }
}
