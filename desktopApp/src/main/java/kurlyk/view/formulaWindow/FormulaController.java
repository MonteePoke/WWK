package kurlyk.view.formulaWindow;


import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import javafx.concurrent.Worker;
import javafx.fxml.FXML;
import javafx.scene.web.WebView;
import kurlyk.transfer.FormulaDto;
import kurlyk.view.common.controller.Controller;
import kurlyk.view.common.controller.TaskBodyController;
import netscape.javascript.JSObject;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.IOException;


@Component
@Scope("prototype")
public class FormulaController extends Controller implements TaskBodyController<FormulaDto> {

    @FXML private WebView browser;
    private JSObject window;


    public void initialize(){
        browser.getEngine().setJavaScriptEnabled(true);
        browser.getEngine().getLoadWorker().stateProperty().addListener((observable, oldValue, newValue) -> {
            if (Worker.State.SUCCEEDED == newValue) {
                window = (JSObject) browser.getEngine().executeScript("window");
            }
        });
        loadWebView();
    }

    private void loadWebView(){
        try {
            browser.getEngine().loadContent(
                    Resources.toString(Resources.getResource("view/formulaWindow/html/index.html"), Charsets.UTF_8),
                    "text/html");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public FormulaDto getResult() {
        return new FormulaDto((String) window.call("getResult"));
    }
}