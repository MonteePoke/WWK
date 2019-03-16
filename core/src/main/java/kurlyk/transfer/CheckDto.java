package kurlyk.transfer;

import kurlyk.exeption.LenghtsNotEqualsExeption;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@EqualsAndHashCode
public class CheckDto {
    private List<String> questions;
    private List<Boolean> correctAnswers;

    public CheckDto() {
        questions = new ArrayList<>();
        correctAnswers = new ArrayList<>();
    }

    public CheckDto(List<String> questions, List<Boolean> correctAnswers) {
        if(questions.size() != correctAnswers.size()){
            throw new LenghtsNotEqualsExeption();
        }
        this.questions = questions;
        this.correctAnswers = correctAnswers;
    }

    public CheckDto(CheckDto checkDto) {
        questions = new ArrayList<>(checkDto.questions);
        correctAnswers = new ArrayList<>(checkDto.correctAnswers);
    }
}
