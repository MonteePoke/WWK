package kurlyk.view.common.stage;

import javafx.stage.Stage;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;


@Component
public class StagePool {

    private Map<Stages, Stage> pool;

    public StagePool() {
        pool = new HashMap<>();
    }

    public void pushStage(Stages key, Stage stage){
        pool.put(key, stage);
    }

    public Stage popStage(Stages key){
        return pool.remove(key);
    }

    public void closeStage(Stages key){
        pool.remove(key).close();
    }
}
