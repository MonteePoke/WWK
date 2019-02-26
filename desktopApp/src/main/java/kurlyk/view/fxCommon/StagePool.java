package kurlyk.view;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import kurlyk.exeption.StageIsExistExeption;
import kurlyk.exeption.StageIsShowExeption;

import java.util.HashMap;
import java.util.Map;

public class StagePool {
    private static StagePool ourInstance = new StagePool();
    private Map<String, Stage> stageMap = new HashMap<>();
    private StageEnum currentShowesStage;

    public static StagePool getInstance() {
        return ourInstance;
    }

    private StagePool() { }

    public void addStage(StageEnum stageEnum, Stage stage){
        if (!stageMap.containsKey(stageEnum.name())) {
            stageMap.put(stageEnum.name(), stage);
        } else {
            throw new StageIsExistExeption(stageEnum.name());
        }
    }

    public void deleteStage(StageEnum stageEnum){
        if (stageEnum.equals(currentShowesStage)){
            throw new StageIsShowExeption(stageEnum.name());
        } else {
            stageMap.remove(stageEnum.name());
        }
    }

    public Stage getStage(StageEnum stageEnum){
        return stageMap.get(stageEnum.name());
    }

    public void showStage(StageEnum stageEnum){
        currentShowesStage = stageEnum;
        getStage(stageEnum).show();
    }
}
