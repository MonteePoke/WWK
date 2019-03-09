package kurlyk.view.common.stage;

import javafx.stage.Modality;
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

    public void pushStageAndShow(Stages key, Stage stage){
        pushStage(key, stage);
        stage.show();
    }

    public void pushStageAndShowModal(Stages key, Stage parentStage, Stage stage){
        pushStage(key, stage);
        stage.initOwner(parentStage);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }

    public Stage popStage(Stages key){
        return pool.remove(key);
    }

    public Stage getStage(Stages key){
        return pool.get(key);
    }

    public void closeStage(Stages key){
        pool.remove(key).close();
    }
}
