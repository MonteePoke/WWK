package kurlyk.view.common.component;


import javafx.scene.control.TextField;

public class OnlyDoubleTextField extends TextField {

    public OnlyDoubleTextField() {
        this(0d);
    }

    public OnlyDoubleTextField(Double number) {
        super(number.toString());
        textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.matches("\\d{0,1}([.]\\d{0,4})?") && !newValue.isEmpty() && !newValue.equals(".")) {
                setText(newValue);
            } else {
                setText(oldValue);
            }
        });
    }

    public double getDouble(){
        return Double.parseDouble(getText());
    }
}
