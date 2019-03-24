package kurlyk.transfer.tasks;

import kurlyk.exeption.LenghtsNotEqualsExeption;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@EqualsAndHashCode
public class MatchingDto implements Serializable {

    private List<String> leftPart;
    private List<String> rightPart;

    public MatchingDto() {
        leftPart = new ArrayList<>();
        rightPart = new ArrayList<>();
    }

    public MatchingDto(List<String> leftPart, List<String> rightPart) {
        if(leftPart.size() != rightPart.size()){
            throw new LenghtsNotEqualsExeption();
        }
        this.leftPart = leftPart;
        this.rightPart = rightPart;
    }

    public MatchingDto(MatchingDto matchingDto) {
        leftPart = new ArrayList<>(matchingDto.leftPart);
        rightPart = new ArrayList<>(matchingDto.rightPart);
    }
}
