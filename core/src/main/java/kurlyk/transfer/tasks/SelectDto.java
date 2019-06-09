package kurlyk.transfer.tasks;

import javafx.util.Pair;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
public class SelectDto implements Serializable {

    private List<Pair<String, Boolean>> options;

    public SelectDto() {
        options = new ArrayList<>();
    }

    public SelectDto(List<Pair<String, Boolean>> options) {
        this.options = options;
    }

    public SelectDto(SelectDto selectDto) {
        options = new ArrayList<>(selectDto.options);
    }
}
