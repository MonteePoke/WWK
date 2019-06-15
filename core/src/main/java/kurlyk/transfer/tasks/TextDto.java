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

    public static TextDto formStrings(String text){
        return new TextDto(String.join(ANSWER_SEPARATOR, text.split("\n")));
    }

    public String toStrings(){
        return String.join("\n", text.split(ANSWER_SEPARATOR));
    }
}
