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
import kurlyk.model.Question;
import kurlyk.transfer.ResultAnswerDto;
import kurlyk.transfer.answer.FormulaAnswerDto;
import kurlyk.transfer.tasks.FormulaDto;
import kurlyk.view.common.stage.StagePool;
import kurlyk.view.common.stage.Stages;
import kurlyk.view.components.MyHtmlEditor;
import kurlyk.view.task.SubmitConfigurationController;
import kurlyk.view.task.TabPurpose;
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
    @FXML private Button sumButton;
    @FXML private Button prodButton;
    @FXML private Button integralButton;
    @FXML private Button powerButton;
    @FXML private Button squareButton;
    @FXML private Button rootButton;
    @FXML private Button squareRootButton;
    @FXML private Button indexButton;
    private JSObject window;
    private Question question;
    private Runnable latexFormulaSetter;

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
                if(latexFormulaSetter != null){
                    latexFormulaSetter.run();
                }
            }
        });
        loadContent(browser);

        sumButton.setOnAction(event -> setLatexFormula("\\sum"));
        prodButton.setOnAction(event -> setLatexFormula("\\prod"));
        integralButton.setOnAction(event -> setLatexFormula("\\int"));
        powerButton.setOnAction(event -> setLatexFormula("^x"));
        squareButton.setOnAction(event -> setLatexFormula("^2"));
        rootButton.setOnAction(event -> setLatexFormula("\\nthroot[y]{x}"));
        squareRootButton.setOnAction(event -> setLatexFormula("\\sqrt{x}"));
        indexButton.setOnAction(event -> setLatexFormula("_x"));
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

    public void setQuestion(Question question, boolean editable, Consumer<Question> callbackActionBefore, Consumer<Question> callbackActionAfter, Stages stageForClose, TabPurpose tabPurpose) {
        this.question = question;
        FormulaDto formulaDto = new Gson().fromJson(question.getAnswer(), FormulaDto.class);
        submitConfiguration(
                editable,
                question,
                submit,
                communicator,
                stagePool,
                callbackActionBefore,
                callbackActionAfter,
                tabPurpose
        );
        setStageForClose(stageForClose);

        //Настройки работчего поля
        textArea.setDisable(!editable);
        textArea.setHtmlText(question.getQuestion());
        if (formulaDto != null) {
            latexFormulaSetter = () -> setLatexFormula(formulaDto.getLatexFormula());
            try {
                latexFormulaSetter.run();
            } catch (NullPointerException ignored) {
            }
        }
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