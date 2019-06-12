package kurlyk.common;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class MyFunction <T> {

    private Consumer<T> consumer;
    private Supplier<T> supplier;

    public MyFunction(Consumer<T> consumer, Supplier<T> supplier) {
        this.consumer = consumer;
        this.supplier = supplier;
    }

    public MyFunction(Consumer<T> consumer) {
        this.consumer = consumer;
        this.supplier = null;
    }

    public MyFunction(Supplier<T> supplier) {
        this.consumer = null;
        this.supplier = supplier;
    }

    public void accept(T object){
        if(consumer != null) {
            consumer.accept(object);
        }
    }

    public T get(){
        return supplier != null ? supplier.get() : null;
    }

    public boolean isConsumer(){
        return consumer != null;
    }

    public boolean isSupplier(){
        return supplier != null;
    }

    public boolean isConsumerSupplier(){
        return consumer != null && supplier != null;
    }
}
