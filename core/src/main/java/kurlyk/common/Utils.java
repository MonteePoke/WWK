package kurlyk.common;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

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

    @SuppressWarnings("unchecked")
    public static <T> T cloneObject(T orig) {
        T obj;
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream out = new ObjectOutputStream(bos);
            out.writeObject(orig);
            out.flush();
            out.close();

            ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(bos.toByteArray()));
            obj = (T) in.readObject();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return obj;
    }
}
