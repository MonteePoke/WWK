package kurlyk.view.components.fields;

public abstract class AbstractNumberField<T extends Number> extends AbstractCustomField <T> {
    private SignDeterminant signDeterminant;

    protected AbstractNumberField(T value, SignDeterminant signDeterminant) {
        super(value);
        this.signDeterminant = signDeterminant;
    }

    protected AbstractNumberField(SignDeterminant signDeterminant) {
        this(null, signDeterminant);
    }

    protected AbstractNumberField(T value) {
        this(value, SignDeterminant.RARIONAL_ONLY);
    }

    protected AbstractNumberField() {
        this(null, SignDeterminant.RARIONAL_ONLY);
    }

    public SignDeterminant getSignDeterminant() {
        return signDeterminant;
    }

    public void setSignDeterminant(SignDeterminant signDeterminant) {
        this.signDeterminant = signDeterminant;
    }
}
