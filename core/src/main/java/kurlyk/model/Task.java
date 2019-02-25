package kurlyk.model;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Task {

    private String request;
    private String response;
    private boolean isReady;
    private int mark;
}
