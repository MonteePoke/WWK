package kurlyk.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@NoArgsConstructor
public class Progres {

    private int currentTaskIndex;
    private List<Task> tasks;
}
