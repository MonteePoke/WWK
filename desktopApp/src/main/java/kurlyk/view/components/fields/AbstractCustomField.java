package kurlyk.view.components.fields;

import javafx.scene.control.TextField;

public abstract class AbstractCustomField <T> extends TextField {

    protected AbstractCustomField(T value) {
        super();
        setText(value != null ? converter(value) : "");
        setFocusTraversable(false);
        textProperty().addListener((observable, oldValue, newValue) -> {
            setText(filter(newValue) ? newValue : oldValue);
        });
    }

    protected AbstractCustomField() {
        this(null);
    }

    public T getValue(){
        return converter(getText());
    }

    public void setValue(T value){
        setText(converter(value));
    }

    //Фильтр
    protected abstract boolean filter(String str);

    //Конвертер для перевода напечатанногог текста в тип T
    protected abstract T converter(String text);

    //Конвертер для перевода типа T в текст
    protected abstract String converter(T value);
}
