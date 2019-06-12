package kurlyk.transfer.tasks;

import kurlyk.common.Trio;
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

    private List<Trio<Boolean, String, Integer>> options;

    public SelectDto() {
        options = new ArrayList<>();
    }

    public SelectDto(List<Trio<Boolean, String, Integer>> options) {
        this.options = options;
    }

    public SelectDto(SelectDto selectDto) {
        options = new ArrayList<>(selectDto.options);
    }
}
