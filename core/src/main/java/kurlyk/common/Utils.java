package kurlyk.common;

import kurlyk.exeption.LenghtsNotEqualsExeption;
import kurlyk.model.base.Dictionary;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
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

    public static <IN_ONE, IN_TWO, OUT> List<OUT> collectTwoLists(List<IN_ONE> list1, List<IN_TWO> list2, BiFunction<IN_ONE, IN_TWO, OUT> converter) {
        if(list1.size() != list2.size()){
            throw new LenghtsNotEqualsExeption();
        }
        List<OUT> outList = new ArrayList<>();
        for(int i = 0; i < list1.size(); i++){
            outList.add(converter.apply(list1.get(i), list2.get(i)));
        }
        return outList;
    }

    public static <IN, OUT> List<OUT> listToListConverter(List<IN> inputList, Function<IN, OUT> converter) {
        List<OUT> ouputList= new ArrayList<>();
        inputList.forEach(in -> ouputList.add(converter.apply(in)));
        return ouputList;
    }

    public static <T> boolean nameIsExist(List<Dictionary<T>> dictionaries, T testName){
        return dictionaries
                .stream()
                .map(Dictionary::getName)
                .anyMatch(name -> name.equals(testName));
    }

    public static <OUT> List<OUT> listToListCaster(List<? extends OUT> inputList){
        return (List<OUT>) inputList;
    }
}
