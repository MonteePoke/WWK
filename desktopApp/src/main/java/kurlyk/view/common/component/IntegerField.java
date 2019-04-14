package kurlyk.view.common.component;


import javafx.scene.control.TextField;

public class IntegerField extends TextField {

    private IntegerField(String text) {
        super(text);
        setFocusTraversable(false);
        textProperty().addListener((observable, oldValue, newValue) -> {
            setText(numberFilter(newValue) ? newValue : oldValue);
        });
    }

    public IntegerField(Integer number) {
        this(number == null ? "" : number.toString());
    }

    public IntegerField() {
        this("");
    }

    public int getNumber(){
        return Integer.parseInt(getText().isEmpty() || getText().equals("-") ? "0" : getText());
    }

    public void setNumber(int number){
        setText(Integer.toString(number));
    }

    private boolean numberFilter(String str){
        if (!str.matches("-?\\d*")){
            return false;
        }
        return true;
    }
}
