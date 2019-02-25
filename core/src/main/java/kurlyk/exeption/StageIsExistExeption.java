package kurlyk.exeption;

public class StageIsExistExeption extends RuntimeException{

    public StageIsExistExeption(String message, Throwable exeption) {
        super("Stage " + message + " is exist", exeption);
    }

    public StageIsExistExeption(String message) {
        super("Stage " + message + " is exist");
    }
}
