package kurlyk.transfer.tasks;

import lombok.Getter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
public class SortingDto implements Serializable{

    private List<String> items;

    public SortingDto() {
        items = new ArrayList<>();
    }

    public SortingDto(List<String> items) {
        this.items = items;
    }

    public SortingDto(SortingDto sortingDto) {
        items = new ArrayList<>(sortingDto.items);
    }

    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SortingDto that = (SortingDto) o;

        if (this.items.size() != that.items.size()){
            return false;
        }

        for(int i = 0; i < this.items.size(); i++){
            if(!this.items.get(i).equals(that.items.get(i))){
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        int result = 0;
        result = 17 * result + Objects.hashCode(items);
        return result;
    }
}
