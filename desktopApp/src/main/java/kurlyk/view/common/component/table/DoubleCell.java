package kurlyk.view.common.component.table;

import javafx.scene.control.TableCell;
import javafx.scene.input.KeyCode;
import kurlyk.view.common.component.DoubleField;

public class DoubleCell<T> extends TableCell<T, Double> {
    private DoubleField doubleField;
    private Runnable commitChanges;

    public DoubleCell(Runnable commitChanges) {
        this.commitChanges = commitChanges;
    }

    @Override
    public void startEdit() {
        if (!isEmpty()) {
            super.startEdit();
            createTextField();
            setText(null);
            setGraphic(doubleField);
            doubleField.selectAll();
        }
    }

    @Override
    public void cancelEdit() {
        super.cancelEdit();

        setText(getItem().toString());
        setGraphic(null);
    }

    @Override
    public void updateItem(Double item, boolean empty) {
        super.updateItem(item, empty);

        if (empty) {
            setText(null);
            setGraphic(null);
        } else {
            if (isEditing()) {
                if (doubleField != null) {
                    doubleField.setText(getString());
                }
                setText(null);
                setGraphic(doubleField);
            } else {
                setText(getString());
                setGraphic(null);
            }
        }
    }

    private void createTextField() {
        doubleField = new DoubleField(getItem());
        doubleField.setMinWidth(this.getWidth() - this.getGraphicTextGap()* 2);
        doubleField.addEventHandler(javafx.scene.input.KeyEvent.KEY_RELEASED, event -> {
            if (KeyCode.ENTER == event.getCode()) {
                commitEdit(doubleField.getNumber());
                commitChanges.run();
            }
        });
    }

    private String getString() {
        return getItem() == null ? "" : getItem().toString();
    }
}
