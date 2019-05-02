package kurlyk.view.components.toolbar;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import kurlyk.communication.Communicator;
import kurlyk.view.common.controller.Controller;
import kurlyk.view.common.stage.StagePool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class ToolbarController extends Controller {
    @FXML private ChoiceBox<String> discipline;
    @FXML private Button questionDictionary;
    @FXML private Button labWorks;
    @FXML private Button delete;
    @FXML private Button edit;
    @FXML private Button add;
    @FXML private Button exportReport;
    @FXML private Button executeLab;
    @FXML private Button help;
    @FXML private Button roles;
    @FXML private Button users;
    @FXML private Button exit;
    @FXML private Button settings;

    @Autowired
    private StagePool stagePool;

    @Autowired
    private Communicator communicator;


    public void initialize() {
        discipline.setItems(FXCollections.observableArrayList("ВВК"));
//        questionDictionary.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/view/components/toolbar/icons/questionDictionary.png"))));
        questionDictionary.setOnAction(event -> {

        });
        labWorks.setOnAction(event -> {

        });
        delete.setOnAction(event -> {

        });
        edit.setOnAction(event -> {

        });
        add.setOnAction(event -> {

        });
        exportReport.setOnAction(event -> {

        });
        executeLab.setOnAction(event -> {

        });
        help.setOnAction(event -> {

        });
        roles.setOnAction(event -> {

        });
        users.setOnAction(event -> {

        });
        exit.setOnAction(event -> {

        });
        settings.setOnAction(event -> {

        });
    }
}