package kurlyk.view.components;


import javafx.scene.control.TextField;

public class DoubleField extends TextField {

    private DoubleField(String text) {
        super(text);
        setFocusTraversable(false);
        textProperty().addListener((observable, oldValue, newValue) -> {
            setText(numberFilter(newValue) ? newValue : oldValue);
        });
    }

    public DoubleField(Double number) {
        this(number == null ? "" : number.toString());
    }

    public DoubleField() {
        this("");
    }

    public double getNumber(){
        return Double.parseDouble(getText().isEmpty() || getText().equals("-") ? "0" : getText());
    }

    public void setNumber(double number){
        setText(Double.toString(number));
    }

    private boolean numberFilter(String str){
        if (!str.matches("-?\\d*\\.?\\d*")){
            return false;
        }
        if (str.equals("-.") || str.equals(".")){
            return false;
        }
        return true;
    }
}
