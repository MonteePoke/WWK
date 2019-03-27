package kurlyk.view.createLabWindow;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.util.Pair;
import kurlyk.QuestionType;
import kurlyk.communication.UserInfo;
import kurlyk.transfer.QuestionDto;
import kurlyk.transfer.tasks.*;
import kurlyk.view.common.controller.Controller;
import kurlyk.view.common.stage.StagePool;
import kurlyk.view.common.stage.Stages;
import kurlyk.view.task.checkWindow.CheckSceneCreator;
import kurlyk.view.task.computerSystemDiagramWindow.ComputerSystemDiagramSceneCreator;
import kurlyk.view.task.formulaWindow.FormulaSceneCreator;
import kurlyk.view.task.matchingWindow.MatchingSceneCreator;
import kurlyk.view.task.numberWindow.NumberSceneCreator;
import kurlyk.view.task.radioWindow.RadioSceneCreator;
import kurlyk.view.task.textWindow.TextSceneCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Scope("prototype")
public class CreateLabController extends Controller {

    @FXML private TextField name;
    @FXML private ComboBox<Integer> labNumber;
    @FXML private ComboBox<QuestionType> labType;
    @FXML private Button further;


    @Autowired
    private StagePool stagePool;

    @Autowired
    private UserInfo userInfo;


    public void initialize(){
        labNumber.getItems().addAll(1, 2, 3, 4, 5, 6, 7);
        labType.getItems().addAll(
                QuestionType.COMPUTER_SYSTEM,
                QuestionType.FORMULA,
                QuestionType.TEXT,
                QuestionType.NUMBER,
                QuestionType.MATCHING,
                QuestionType.CHEK,
                QuestionType.RADIO
        );

        further.setOnAction(event -> {
            QuestionDto questionDto = QuestionDto.builder()
                    .name(name.getText())
                    .labNumber(labNumber.getValue())
                    .questionType(labType.getValue())
                    .build();
            Scene scene = null;
            switch (questionDto.getQuestionType()){
                case RADIO:
                    SelectDto radioDto = new SelectDto(Arrays.asList(
                            new Pair<>("", false),
                            new Pair<>("", false),
                            new Pair<>("", false),
                            new Pair<>("", false)
                    ));
                    scene = new RadioSceneCreator(questionDto, radioDto, true).getScene();
                    break;
                case CHEK:
                    SelectDto chekDto = new SelectDto(Arrays.asList(
                            new Pair<>("", false),
                            new Pair<>("", false),
                            new Pair<>("", false),
                            new Pair<>("", false)
                    ));
                    scene = new CheckSceneCreator(questionDto, chekDto, true).getScene();
                    break;
                case MATCHING:
                    MatchingDto matchingDto = new MatchingDto(
                            Arrays.asList("", "", "", ""),
                            Arrays.asList("", "", "", "")
                    );
                    scene = new MatchingSceneCreator(questionDto, matchingDto, true).getScene();
                    break;
                case NUMBER:
                    NumberDto numberDto = new NumberDto();
                    scene = new NumberSceneCreator(questionDto, numberDto, true).getScene();
                    break;
                case TEXT:
                    TextDto textDto = new TextDto();
                    scene = new TextSceneCreator(questionDto, textDto, true).getScene();
                    break;
                case FORMULA:
                    FormulaDto formulaDto = new FormulaDto();
                    scene = new FormulaSceneCreator(questionDto, formulaDto, true).getScene();
                    break;
                case COMPUTER_SYSTEM:
                    ComputerSystemDto computerSystemDto = new ComputerSystemDto();
                    scene = new ComputerSystemDiagramSceneCreator(questionDto, computerSystemDto, true).getScene();
                    break;
            }
            stagePool.getStage(Stages.CREATE_LAB).setScene(scene);
        });
    }
}