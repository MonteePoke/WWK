package kurlyk.view.task.formulaWindow;


import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import javafx.concurrent.Worker;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebView;
import kurlyk.communication.Communicator;
import kurlyk.models.UserProgress;
import kurlyk.transfer.tasks.FormulaDto;
import kurlyk.view.common.component.MyHTMLEditor;
import kurlyk.view.common.stage.StagePool;
import kurlyk.view.task.CommonTaskController;
import netscape.javascript.JSObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.IOException;


@Component
@Scope("prototype")
public class FormulaController extends CommonTaskController<FormulaDto> {

    @FXML private VBox root;
    @FXML private Button submit;
    @FXML private MyHTMLEditor textArea;
    @FXML private WebView browser;
    private JSObject window;

    @Autowired
    private Communicator communicator;

    @Autowired
    private StagePool stagePool;


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

    public void setQuestion(UserProgress userProgress, FormulaDto formulaDto, boolean editable) {
        final FormulaDto rightFormulaDto = formulaDto;
        commonConfiguration(
                userProgress,
                () -> isRightAnswer(rightFormulaDto, userProgress),
                editable,
                textArea,
                submit,
                communicator,
                stagePool
        );
//        if (editable && formulaDto.getLatexFormula() != null) {
//            inputField.setText(formulaDto.getLatexFormula());
//        }
    }

    private Double isRightAnswer(FormulaDto formulaDto, UserProgress userProgress){
        double score = 0d;
        if (formulaDto.equals(getResult())){
            score = userProgress.getTask().getScore() * userProgress.getQuestion().getScore();
        }
        return score;
    }

    @Override
    public FormulaDto getResult() {
        return new FormulaDto((String) window.call("getResult"));
    }
}