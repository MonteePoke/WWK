package kurlyk.transfer;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@EqualsAndHashCode
public class RadioDto implements Serializable {

    private List<String> questions;
    @Setter private Integer correctAnswer;

    public RadioDto() {
        questions = new ArrayList<>();
        correctAnswer = null;
    }

    public RadioDto(List<String> questions, Integer correctAnswer) {
        if (correctAnswer >= questions.size() || correctAnswer < 0){
            throw new IndexOutOfBoundsException();
        }
        this.questions = questions;
        this.correctAnswer = correctAnswer;
    }

    public RadioDto(RadioDto radioDto) {
        questions = new ArrayList<>(radioDto.questions);
        correctAnswer = radioDto.getCorrectAnswer();
    }
}
