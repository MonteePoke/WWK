package kurlyk.view.formulaWindow;


import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import javafx.concurrent.Worker;
import javafx.fxml.FXML;
import javafx.scene.web.WebView;
import kurlyk.view.common.controller.Controller;
import netscape.javascript.JSObject;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.IOException;


@Component
@Scope("prototype")
public class FormulaController extends Controller {

    @FXML private WebView browser;

    public class Bridge {
        public void showTime(String str) {
            System.out.println(str);
        }
    }

    public void initialize(){
        browser.getEngine().setJavaScriptEnabled(true);

        browser.getEngine().getLoadWorker().stateProperty().addListener((observable, oldValue, newValue) -> {
            if (Worker.State.SUCCEEDED == newValue) {
                JSObject window = (JSObject) browser.getEngine().executeScript("window");
                window.setMember("myJavaMember", new Bridge());
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
}