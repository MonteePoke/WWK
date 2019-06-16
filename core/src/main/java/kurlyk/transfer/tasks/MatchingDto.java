package kurlyk.transfer.tasks;

import kurlyk.exeption.LenghtsNotEqualsExeption;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.*;

@Getter
@Setter
public class MatchingDto implements Serializable{

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

    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MatchingDto that = (MatchingDto) o;

        Map<String, String> thisLeftRightSum = new HashMap<>();
        for(int i = 0; i < leftPart.size(); i++){
            thisLeftRightSum.put(leftPart.get(i), rightPart.get(i));
        }
        Map<String, String> thatLeftRightSum = new HashMap<>();
        for(int i = 0; i < that.leftPart.size(); i++){
            thatLeftRightSum.put(that.leftPart.get(i), that.rightPart.get(i));
        }
        if (!thisLeftRightSum.equals(thatLeftRightSum)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = 0;
        result = 17 * result + Objects.hashCode(leftPart);
        result = 17 * result + Objects.hashCode(rightPart);
        return result;
    }
}
