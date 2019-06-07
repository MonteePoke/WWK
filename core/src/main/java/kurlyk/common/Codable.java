package kurlyk.common;

import java.util.function.Predicate;

/**
 * Интерфейс для перечислений с кодом.
 *
 * @author Alexander Rysev
 */
public interface Codable<S> {
    S getCode();

    /**
     * Выполняет поиск значения в перечислении по коду.
     *
     * @param codableSet класс - перечисление
     * @param code       код
     * @param <T>        перечисления
     * @return найденное значение перечилсения
     * @throws IllegalArgumentException если значение с данным кодом не найдено
     */
    static <T extends Enum<T> & Codable<S>, S> T find(Class<T> codableSet, S code) {
        if (code == null){
            return null;
        }
        for (T codable : codableSet.getEnumConstants()) {
            if (codable.getCode().equals(code)) {
                return codable;
            }
        }
        throw new IllegalArgumentException(
                String.format("%s type not found for code = %s", codableSet.getName(), code));
    }

    /**
     * Выполняет поиск значения в перечислении по коду, если значение не найдено - то возвращает значение по умолчанию.
     *
     * @param codableSet   класс - перечисление
     * @param code         код
     * @param defaultValue значение по умолчанию
     * @param <T>          перечисления
     * @return найденное значение перечилсения
     */
    static <T extends Enum<T> & Codable<S>, S> T find(Class<T> codableSet, S code, T defaultValue) {
        for (T codable : codableSet.getEnumConstants()) {
            if (codable.getCode().equals(code)) {
                return codable;
            }
        }
        return defaultValue;
    }

    /**
     * Выполняет проверку существования значения по его коду.
     *
     * @param codableSet класс - перечисление
     * @param code       код
     * @param <T>        перечисления
     * @return Если код существует - true иначе false
     */
    static <T extends Enum<T> & Codable<S>, S> boolean exists(Class<T> codableSet, S code) {
        for (T codable : codableSet.getEnumConstants()) {
            if (codable.getCode().equals(code)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Воздаёт функцию проверки наличия данного значения кода, в наборе объектов
     *
     * @param source Коллекция или объект содержащий значения
     * @return Функция проверки принадлежности значения кода в коллекции кодов.
     */
    static <T> Predicate<T> containsPredicate(Iterable<? extends Codable<T>> source) {
        return value -> {
            if (value == null) {
                return false;
            }
            for (Codable<T> object : source) {
                if (value.equals(object.getCode())) {
                    return true;
                }
            }
            return false;
        };
    }
}