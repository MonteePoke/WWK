package kurlyk.transfer.tasks;

import javafx.util.Pair;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@EqualsAndHashCode
public class SelectDto implements Serializable {

    private List<Pair<String, Boolean>> questions;

    public SelectDto() {
        questions = new ArrayList<>();
    }

    public SelectDto(List<Pair<String, Boolean>> questions) {
        this.questions = questions;
    }

    public SelectDto(SelectDto selectDto) {
        questions = new ArrayList<>(selectDto.questions);
    }
}
