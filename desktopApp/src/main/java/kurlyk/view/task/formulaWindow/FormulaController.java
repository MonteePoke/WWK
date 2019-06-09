package kurlyk.view.task.formulaWindow;


import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import com.google.gson.Gson;
import javafx.concurrent.Worker;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebView;
import kurlyk.communication.Communicator;
import kurlyk.communication.UsverInfo;
import kurlyk.models.Question;
import kurlyk.transfer.ResultAnswerDto;
import kurlyk.transfer.answer.FormulaAnswerDto;
import kurlyk.transfer.tasks.FormulaDto;
import kurlyk.view.common.stage.StagePool;
import kurlyk.view.components.MyHtmlEditor;
import kurlyk.view.task.SubmitConfigurationController;
import netscape.javascript.JSObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.function.Consumer;


@Component
@Scope("prototype")
public class FormulaController extends SubmitConfigurationController<FormulaDto> {

    @FXML private VBox root;
    @FXML private Button submit;
    @FXML private MyHtmlEditor textArea;
    @FXML private WebView browser;
    private JSObject window;
    private Question question;

    @Autowired
    private Communicator communicator;

    @Autowired
    private StagePool stagePool;

    @Autowired
    private UsverInfo usverInfo;


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

    public void setQuestion(Question question, boolean editable, Consumer<Question> callbackAction) {
        this.question = question;
        FormulaDto formulaDto = new Gson().fromJson(question.getAnswer(), FormulaDto.class);
        submitConfiguration(
                editable,
                question,
                submit,
                communicator,
                stagePool,
                callbackAction
        );

        //Настройки работчего поля
        textArea.setDisable(!editable);
        textArea.setHtmlText(question.getQuestion());
        setLatexFormula(formulaDto.getLatexFormula());
    }

    @Override
    public FormulaDto getResult() {
        return new FormulaDto(getLatexFormula());
    }

    @Override
    public String getQuestionText() {
        return textArea.getHtmlText();
    }

    @Override
    public ResultAnswerDto getAnswerResult(Integer attempt) throws IOException {
        return communicator.testFormulaAnswer(
                FormulaAnswerDto
                        .builder()
                        .entity(getResult())
                        .usverId(usverInfo.getTokenDto().getUsverId())
                        .questionId(question.getId())
                        .attemptsNumber(attempt)
                        .build()
        );
    }

    private String getLatexFormula(){
        return (String) window.call("getResult");
    }

    private void setLatexFormula(String string){
        window.call("setResult", string);
    }
}