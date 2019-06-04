package kurlyk.view.components.toolbar;


import javafx.scene.control.TextField;

public class LongField extends TextField {

    private LongField(String text) {
        super(text);
        setFocusTraversable(false);
        textProperty().addListener((observable, oldValue, newValue) -> {
            setText(numberFilter(newValue) ? newValue : oldValue);
        });
    }

    public LongField(Long number) {
        this(number == null ? "" : number.toString());
    }

    public LongField() {
        this("");
    }

    public Long getNumber(){
        return Long.parseLong(getText().isEmpty() || getText().equals("-") ? "0" : getText());
    }

    public void setNumber(long number){
        setText(Long.toString(number));
    }

    private boolean numberFilter(String str){
        if (!str.matches("-?\\d*")){
            return false;
        }
        return true;
    }
}
