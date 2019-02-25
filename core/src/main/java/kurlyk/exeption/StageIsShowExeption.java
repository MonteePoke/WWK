package kurlyk.exeption;

public class StageIsShowExeption extends RuntimeException{

    public StageIsShowExeption(String message, Throwable exeption) {
        super("Stage " + message + " is showes", exeption);
    }

    public StageIsShowExeption(String message) {
        super("Stage " + message + " is showes");
    }
}
