package kurlyk.view.common.component.table;

import javafx.scene.control.TableCell;
import javafx.scene.input.KeyCode;
import kurlyk.view.common.component.IntegerField;

public class IntegerCell<T> extends TableCell<T, Integer> {
    private IntegerField integerField;
    private Runnable commitChanges;

    public IntegerCell(Runnable commitChanges) {
        this.commitChanges = commitChanges;
    }

    @Override
    public void startEdit() {
        if (!isEmpty()) {
            super.startEdit();
            createTextField();
            setText(null);
            setGraphic(integerField);
            integerField.selectAll();
        }
    }

    @Override
    public void cancelEdit() {
        super.cancelEdit();

        setText(getItem().toString());
        setGraphic(null);
    }

    @Override
    public void updateItem(Integer item, boolean empty) {
        super.updateItem(item, empty);

        if (empty) {
            setText(null);
            setGraphic(null);
        } else {
            if (isEditing()) {
                if (integerField != null) {
                    integerField.setText(getString());
                }
                setText(null);
                setGraphic(integerField);
            } else {
                setText(getString());
                setGraphic(null);
            }
        }
    }

    private void createTextField() {
        integerField = new IntegerField(getItem());
        integerField.setMinWidth(this.getWidth() - this.getGraphicTextGap()* 2);
        integerField.addEventHandler(javafx.scene.input.KeyEvent.KEY_RELEASED, event -> {
            if (KeyCode.ENTER == event.getCode()) {
                commitEdit(integerField.getNumber());
                commitChanges.run();
            }
        });
    }

    private String getString() {
        return getItem() == null ? "" : getItem().toString();
    }
}
