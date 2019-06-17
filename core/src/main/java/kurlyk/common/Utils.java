package kurlyk.common;

import kurlyk.exeption.LenghtsNotEqualsExeption;
import kurlyk.model.base.Dictionary;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Utils {

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

    public static <T> List<T> joinTwoList(List<T> list1, List<T> list2) {
        List<T> result = new ArrayList<>();
        result.addAll(list1);
        result.addAll(list2);
        return result;
    }

    public static <IN_ONE, IN_TWO, OUT> List<OUT> collectTwoList(List<IN_ONE> list1, List<IN_TWO> list2, BiFunction<IN_ONE, IN_TWO, OUT> converter) {
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

    public static List<Duet<Double, Double>> splitRange(double from, double to, int numberOfParts){
        List<Duet<Double, Double>> result = new ArrayList<>(numberOfParts);
        double unit = (from - to) / numberOfParts;
        for(int i = 0; i < numberOfParts - 1; i++){
            result.add(new Duet<>(from + i * unit, from + (i + 1) * unit));
        }
        result.add(new Duet<>(to + (numberOfParts - 1) * unit, to));
        return result;
    }

    public static List<Double> getRandomNumbersFromRanges(List<Duet<Double, Double>> ranges){
        return ranges
                .stream()
                .map(duet -> duet.getA() + Math.random() * (duet.getB() - duet.getA()))
                .collect(Collectors.toList());
    }

    public static List<Double> getRandomNumbersFromRanges(double from, double to, int numberOfParts){
        return getRandomNumbersFromRanges(splitRange(from, to, numberOfParts));
    }

    public static Date toDate(LocalDateTime localDateTime){
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static LocalDateTime toLocalDateTime(Date date){
        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
    }

    public static <T> boolean setsEquals(Set<T> set1, Set<T> set2, BiPredicate<T, T> comparator){
        if(set1.size() != set2.size()){
            return false;
        }
        boolean result = false;
        for (T element1 : set1){
            for (T element2 : set2){
                result = comparator.test(element1, element2);
                if(result){
                    break;
                }
            }
            if(!result){
                break;
            }
        }
        return result;
    }
}
