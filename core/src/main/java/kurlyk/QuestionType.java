package kurlyk;

import kurlyk.common.Codable;

public enum QuestionType implements Codable<String> {
    COMPUTER_SYSTEM("Схема"),

    FORMULA("Формула"),
    TEXT("Текст"),
    NUMBER("Числовое значение"),

    SORTING("Сортировка"),
    MATCHING("Соответствие"),
    CHECK("Множественный выбор"),
    RADIO("Одиночный выбор");

    private String code;

    QuestionType(String code) {
        this.code = code;
    }

    @Override
    public String getCode() {
        return code;
    }
}
