package kurlyk.exeption;

public class InitializatorExeption extends RuntimeException{

    public InitializatorExeption(Exception exeption) {
        super(exeption);
    }
}
