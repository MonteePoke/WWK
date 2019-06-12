package kurlyk.view.components.fields;


public class LongField extends AbstractNumberField<Long> {

    public LongField(Long number, SignDeterminant signDeterminant) {
        super(number, signDeterminant);
    }

    public LongField(SignDeterminant signDeterminant) {
        super(signDeterminant);
    }


    public LongField(Long number) {
        super(number);
    }

    public LongField() {
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
    protected Long converter(String text) {
        return Long.parseLong(text.isEmpty() || text.equals("-") ? "0" : text);
    }

    @Override
    protected String converter(Long value) {
        return value.toString();
    }
}
