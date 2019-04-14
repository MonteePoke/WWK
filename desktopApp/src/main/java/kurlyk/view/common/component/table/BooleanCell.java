package kurlyk.view.common.component.table;

import javafx.scene.control.CheckBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.TableCell;
import javafx.scene.input.KeyCode;

public class BooleanCell <T> extends TableCell<T, Boolean> {
    private CheckBox checkBox;
    private Runnable commitChanges;


    public BooleanCell(Runnable commitChanges) {
        checkBox = new CheckBox();
        checkBox.setDisable(true);
        checkBox.addEventHandler(javafx.scene.input.KeyEvent.KEY_RELEASED, event -> {
            if (KeyCode.ENTER == event.getCode()) {
                if(isEditing()){
                    commitEdit(checkBox.isSelected());
                    commitChanges.run();
                }
            }
        });
        this.setGraphic(checkBox);
        this.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        this.setEditable(true);
        this.commitChanges = commitChanges;
    }
    @Override
    public void startEdit() {
        super.startEdit();
        if (isEmpty()) {
            return;
        }
        checkBox.setDisable(false);
        checkBox.requestFocus();
    }
    @Override
    public void cancelEdit() {
        super.cancelEdit();
        checkBox.setDisable(true);
    }
    public void commitEdit(Boolean value) {
        super.commitEdit(value);
        checkBox.setDisable(true);
    }
    @Override
    public void updateItem(Boolean item, boolean empty) {
        super.updateItem(item, empty);
        if (!isEmpty()) {
            checkBox.setSelected(item);
        }
    }
}
