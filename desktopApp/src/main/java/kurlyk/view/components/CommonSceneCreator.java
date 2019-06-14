package kurlyk.view.components;

import javafx.scene.Scene;
import kurlyk.model.Question;
import kurlyk.view.common.stage.Stages;
import kurlyk.view.task.checkWindow.CheckSceneCreator;
import kurlyk.view.task.computerSystemDiagramWindow.ComputerSystemDiagramSceneCreator;
import kurlyk.view.task.formulaWindow.FormulaSceneCreator;
import kurlyk.view.task.matchingWindow.MatchingSceneCreator;
import kurlyk.view.task.numberWindow.NumberSceneCreator;
import kurlyk.view.task.radioWindow.RadioSceneCreator;
import kurlyk.view.task.sortingWindow.SortingSceneCreator;
import kurlyk.view.task.textWindow.TextSceneCreator;

import java.util.function.Consumer;

public class CommonSceneCreator {

    public static Scene questionSceneCreator(Question question, boolean editable, Consumer<Question> callbackActionBefore, Consumer<Question> callbackActionAfter, Stages stageForClose){
        Scene scene = null;
        switch (question.getQuestionType()) {
            case RADIO:
                scene = new RadioSceneCreator(question, editable, callbackActionBefore, callbackActionAfter, stageForClose).getScene();
                break;
            case CHECK:
                scene = new CheckSceneCreator(question, editable, callbackActionBefore, callbackActionAfter, stageForClose).getScene();
                break;
            case SORTING:
                scene = new SortingSceneCreator(question, editable, callbackActionBefore, callbackActionAfter, stageForClose).getScene();
                break;
            case MATCHING:
                scene = new MatchingSceneCreator(question, editable, callbackActionBefore, callbackActionAfter, stageForClose).getScene();
                break;
            case NUMBER:
                scene = new NumberSceneCreator(question, editable, callbackActionBefore, callbackActionAfter, stageForClose).getScene();
                break;
            case TEXT:
                scene = new TextSceneCreator(question, editable, callbackActionBefore, callbackActionAfter, stageForClose).getScene();
                break;
            case FORMULA:
                scene = new FormulaSceneCreator(question, editable, callbackActionBefore, callbackActionAfter, stageForClose).getScene();
                break;
            case COMPUTER_SYSTEM:
                scene = new ComputerSystemDiagramSceneCreator(question, editable, callbackActionBefore, callbackActionAfter, stageForClose).getScene();
                break;
        }
        return scene;
    }
}
