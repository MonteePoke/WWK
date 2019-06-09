package kurlyk.common;

import javafx.beans.property.SimpleDoubleProperty;

import java.util.Objects;

public class SelfMadeSimpleDoubleProperty extends SimpleDoubleProperty {

    public SelfMadeSimpleDoubleProperty() {
    }

    public SelfMadeSimpleDoubleProperty(double initialValue) {
        super(initialValue);
    }

    public SelfMadeSimpleDoubleProperty(Object bean, String name) {
        super(bean, name);
    }

    public SelfMadeSimpleDoubleProperty(Object bean, String name, double initialValue) {
        super(bean, name, initialValue);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SelfMadeSimpleDoubleProperty that = (SelfMadeSimpleDoubleProperty) o;

        if (!Objects.equals(getValue(), that.getValue())) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (getValue() != null ? getValue().hashCode() : 0);

        return result;
    }
}
