package kurlyk.transfer.tasks;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class TextDto {
    public static final String ANSWER_SEPARATOR = ";";
    private String text;
}
