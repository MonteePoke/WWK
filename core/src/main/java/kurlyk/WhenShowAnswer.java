package kurlyk;

import kurlyk.common.Codable;

/**
 * @author Alexander Rysev
 */
public enum WhenShowAnswer implements Codable<String> {
    NEVER("Никогда"),

    AFTER_FIRST_MISTAKE("После 1 попытки"),
    AFTER_THIRD_MISTAKE("После 3 попыток"),

    ALWAYS("Всегда");

    private String code;

    WhenShowAnswer(String code) {
        this.code = code;
    }

    @Override
    public String getCode() {
        return code;
    }
}
