package kurlyk.view.task.formulaWindow;


import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import com.google.gson.Gson;
import javafx.concurrent.Worker;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebView;
import kurlyk.communication.Communicator;
import kurlyk.communication.UserProgress;
import kurlyk.transfer.QuestionDto;
import kurlyk.transfer.tasks.FormulaDto;
import kurlyk.view.common.controller.Controller;
import kurlyk.view.common.controller.TaskBodyController;
import kurlyk.view.common.stage.StagePool;
import kurlyk.view.common.stage.Stages;
import kurlyk.view.createLabWindow.CreateLabSceneCreator;
import kurlyk.view.utils.FxDialogs;
import netscape.javascript.JSObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.function.Supplier;


@Component
@Scope("prototype")
public class FormulaController extends Controller implements TaskBodyController<FormulaDto> {

    @FXML private VBox root;
    @FXML private Button submit;
    @FXML private TextArea textArea;
    @FXML private WebView browser;
    private JSObject window;

    @Autowired
    private Communicator communicator;

    @Autowired
    private StagePool stagePool;

    @Autowired
    private UserProgress userProgress;

    public void initialize(){
        browser.getEngine().setJavaScriptEnabled(true);
        browser.getEngine().getLoadWorker().stateProperty().addListener((observable, oldValue, newValue) -> {
            if (Worker.State.SUCCEEDED == newValue) {
                window = (JSObject) browser.getEngine().executeScript("window");
            }
        });
        loadContent(browser);
    }

    private void loadContent(WebView browser){
        try {
            browser.getEngine().loadContent(
                    Resources.toString(Resources.getResource("view/task/formulaWindow/html/index.html"), Charsets.UTF_8),
                    "text/html");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setQuestion(QuestionDto questionDto, FormulaDto formulaDto, boolean editable) {
        final FormulaDto rightFormulaDto = formulaDto;
        commonConfiguration(questionDto, () -> isRightAnswer(rightFormulaDto), editable);
//        if (editable && formulaDto.getLatexFormula() != null) {
//            inputField.setText(formulaDto.getLatexFormula());
//        }
    }

    private void commonConfiguration(QuestionDto questionDto, Supplier<Boolean> isRightAnswer, boolean editable) {
        textArea.setEditable(editable);
        if (editable){
            submit.setOnAction(event -> {
                questionDto.setQuestion(textArea.getText());
                questionDto.setAnswer(new Gson().toJson(getResult()));
                try {
                    communicator.postTask(questionDto);
                    stagePool.getStage(Stages.CREATE_LAB).setScene(new CreateLabSceneCreator().getScene());
                } catch (IOException e) {
                    FxDialogs.showError("", "Ошибка отправки данных");
                }
            });
        } else{
            textArea.setText(questionDto.getQuestion());
            submit.setOnAction(event -> {
                userProgress.getProgress().put(questionDto.getId(), isRightAnswer.get() ? 100 : 0);
                FxDialogs.showInformation("Результат", isRightAnswer.get() ? "Верно" : "Неверно");
            });
        }
    }

    public boolean isRightAnswer(FormulaDto formulaDto){
        return formulaDto.equals(getResult());
    }

    @Override
    public FormulaDto getResult() {
        return new FormulaDto((String) window.call("getResult"));
    }
}