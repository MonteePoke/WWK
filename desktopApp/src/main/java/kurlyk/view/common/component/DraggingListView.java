package kurlyk.view.common.component;


import javafx.event.Event;
import javafx.geometry.Pos;
import javafx.scene.control.ListView;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.util.converter.DefaultStringConverter;

import java.util.ArrayList;


public class DraggingListView extends ListView<String> {

    public DraggingListView() {
        super();
        setCellFactory(param -> new BirdCell());
        setEditable(true);
        setOnEditCommit(t -> getItems().set(t.getIndex(), t.getNewValue()));
        setOnEditCancel(t -> System.out.println("setOnEditCancel"));
    }


    private class BirdCell extends TextFieldListCell<String> {

        public BirdCell() {
            super(new DefaultStringConverter());
            setAlignment(Pos.CENTER);

            //Начало
            setOnDragDetected(event -> {
                if (getItem() == null) {
                    return;
                }
                ClipboardContent content = new ClipboardContent();
                content.putString(getItem());
                Dragboard dragboard = startDragAndDrop(TransferMode.MOVE);
                dragboard.setContent(content);
                event.consume();
            });

            //Несём по полю
            setOnDragOver(event -> {
                if (event.getGestureSource() != this && event.getDragboard().hasString()) {
                    event.acceptTransferModes(TransferMode.MOVE);
                }
                event.consume();
            });

            //Принесли
            setOnDragDropped(event -> {
                if (getItem() == null) {
                    return;
                }

                Dragboard dragboard = event.getDragboard();
                if (dragboard.hasString()) {
                    int draggedIdx = getListView().getItems().indexOf(dragboard.getString());
                    int thisIdx = getListView().getItems().indexOf(getItem());
                    getListView().getItems().set(draggedIdx, getItem());
                    getListView().getItems().set(thisIdx, dragboard.getString());

                    getListView().getItems().setAll(new ArrayList<>(getListView().getItems()));

                    event.setDropCompleted(true);
                } else{
                    event.setDropCompleted(false);
                }
                event.consume();
            });

            //Конец
            setOnDragDone(Event::consume);
        }

        //При каждой перерисовке списка
        @Override
        public void updateItem(String item, boolean empty) {
            super.updateItem(item, empty);
            setText((empty || item == null) ? null : item);
        }
    }
}
