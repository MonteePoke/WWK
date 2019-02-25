package kurlyk.common;

public class Utils {

    public static double partOfTheNumber(double percent, double number){
        return number / 100d * percent;
    }

    public static int partOfTheNumber(double percent, int number){
        return (int) partOfTheNumber(percent, new Double(number));
    }

    public static <T> boolean objectIsNotNull(T object){
        return object != null;
    }

    public static <T> boolean objectIsNull(T object){
        return object == null;
    }
}
