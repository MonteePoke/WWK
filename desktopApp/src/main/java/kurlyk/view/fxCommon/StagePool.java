package kurlyk.view.fxCommon;

import javafx.stage.Stage;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;


@Component
public class StagePool {

    private Map<String, Stage> pool;

    public StagePool() {
        pool = new HashMap<>();
    }

    public void pushStage(String key, Stage stage){
        pool.put(key, stage);
    }

    public Stage popStage(String key){
        return pool.remove(key);
    }

    public void closeStage(String key){
        pool.remove(key).close();
    }
}
