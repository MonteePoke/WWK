package kurlyk.common;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class Converter {

    public static <IN, OUT> List<OUT> listToList(List<IN> inputList, Function<IN, OUT> converter) {
        List<OUT> ouputList= new ArrayList<>();
        inputList.forEach(in -> ouputList.add(converter.apply(in)));
        return ouputList;
    }
}
