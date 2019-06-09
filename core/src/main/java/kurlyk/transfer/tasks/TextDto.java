package kurlyk.transfer.tasks;

import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class TextDto {
    public static final String ANSWER_SEPARATOR = ";";
    private String text;
}
