package kurlyk.view.components.fields;


public class DoubleField extends AbstractNumberField<Double> {

    public DoubleField(Double number, SignDeterminant signDeterminant) {
        super(number, signDeterminant);
    }

    public DoubleField(SignDeterminant signDeterminant) {
        super(signDeterminant);
    }


    public DoubleField(Double number) {
        super(number);
    }

    public DoubleField() {
        super();
    }

    @Override
    protected boolean filter(String str) {
        switch (getSignDeterminant()){
            case POSITIVE_ONLY:
                return !str.equals("-.") && !str.equals(".") && str.matches("\\d*\\.?\\d*");
            case NEGATIVE_ONLY:
                return !str.equals("-.") && !str.equals(".") && str.matches("-\\d*\\.?\\d*");
            case RARIONAL_ONLY:
                return !str.equals("-.") && !str.equals(".") && str.matches("-?\\d*\\.?\\d*");
        }
        return false;
    }

    @Override
    protected Double converter(String text) {
        return Double.parseDouble(text.isEmpty() || text.equals("-") ? "0" : text);
    }

    @Override
    protected String converter(Double value) {
        return value.toString();
    }
}
