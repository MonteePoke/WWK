package kurlyk.view.common.component;


import javafx.scene.control.TextField;

public class NumberField extends TextField {

    private NumberField(String text) {
        super(text);
        setFocusTraversable(false);
        textProperty().addListener((observable, oldValue, newValue) -> {
            setText(numberFilter(newValue) ? newValue : oldValue);
        });
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


    public NumberField(Double number) {
        this(number.toString());
    }

    public NumberField() {
        this("");
    }

    public double getNumber(){
        return Double.parseDouble(getText().isEmpty() || getText().equals("-") ? "0" : getText());
    }

    public void setNumber(double number){
        setText(Double.toString(number));
    }
}
