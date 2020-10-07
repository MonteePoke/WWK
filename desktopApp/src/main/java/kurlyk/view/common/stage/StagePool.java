package kurlyk.view.common.stage;

import javafx.scene.Scene;
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

    public void clearStages() {
        for (Map.Entry<Stages,Stage> entry : pool.entrySet()) {
            entry.getValue().close();
        }
        pool.clear();
    }

    public void pushStageAndShowModal(Stages key, Stage stage){
        pushStage(key, stage);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }

    public void showStage(Stages key){
        pool.get(key).show();
    }

    public Stage popStage(Stages key){
        return pool.remove(key);
    }

    public Stage getStage(Stages key){
        return pool.get(key);
    }

    public void setSceneStage(Stages key, Scene scene){
        pool.get(key).setScene(scene);
    }

    public void deleteStage(Stages key){
        pool.remove(key).close();
    }

    public void closeStage(Stages key){
        pool.get(key).close();
    }
}
