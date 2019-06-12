package kurlyk.view.components.fields;


public class IntegerField extends AbstractNumberField<Integer> {

    public IntegerField(Integer number, SignDeterminant signDeterminant) {
        super(number, signDeterminant);
    }

    public IntegerField(SignDeterminant signDeterminant) {
        super(signDeterminant);
    }


    public IntegerField(Integer number) {
        super(number);
    }

    public IntegerField() {
        super();
    }

    @Override
    protected boolean filter(String str) {
        switch (getSignDeterminant()){
            case POSITIVE_ONLY:
                return str.matches("\\d*");
            case NEGATIVE_ONLY:
                return str.matches("-\\d*");
            case RARIONAL_ONLY:
                return str.matches("-?\\d*");
        }
        return false;
    }

    @Override
    protected Integer converter(String text) {
        return Integer.parseInt(text.isEmpty() || text.equals("-") ? "0" : text);
    }

    @Override
    protected String converter(Integer value) {
        return value.toString();
    }
}
