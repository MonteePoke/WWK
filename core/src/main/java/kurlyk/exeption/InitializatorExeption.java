package kurlyk.exeption;

public class InitializatorExeption extends RuntimeException{

    public InitializatorExeption(String message, Throwable exeption) {
        super("Initializing in " + message + " failed", exeption);
    }

    public InitializatorExeption(String message) {
        super("Initializing in " + message + " failed");
    }
}
