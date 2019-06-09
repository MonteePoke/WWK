package kurlyk.common;

import kurlyk.models.base.Dictionary;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

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

    public static <IN, OUT> List<OUT> listToListConverter(List<IN> inputList, Function<IN, OUT> converter) {
        List<OUT> ouputList= new ArrayList<>();
        inputList.forEach(in -> ouputList.add(converter.apply(in)));
        return ouputList;
    }

    public static boolean nameIsExist(List<Dictionary> dictionaries, String testName){
        return dictionaries
                .stream()
                .map(Dictionary::getName)
                .anyMatch(name -> name.equals(testName));
    }

    public static <OUT> List<OUT> listToListCaster(List<? extends OUT> inputList){
        return (List<OUT>) inputList;
    }
}
