package kurlyk.view.components.toolbar;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.stage.Modality;
import kurlyk.communication.Communicator;
import kurlyk.view.common.controller.Controller;
import kurlyk.view.common.stage.StagePool;
import kurlyk.view.create.createTaskWindow.questionListWindow.QuestionListStage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class ToolbarController extends Controller {
    @FXML
    private Pane navigateControls;
    @FXML
    private Pane lectorActionControls;
    @FXML
    private Pane studentActionControls1;
    @FXML
    private Pane adminActions;
    @FXML
    private Pane commonActions;

    @FXML
    private ChoiceBox<String> discipline;
    @FXML
    private Button questionDictionary;
    @FXML
    private Button labWorks;
    @FXML
    private Button delete;
    @FXML
    private Button edit;
    @FXML
    private Button add;
    @FXML
    private Button exportReport;
    @FXML
    private Button executeLab;
    @FXML
    private Button help;
    @FXML
    private Button roles;
    @FXML
    private Button users;
    @FXML
    private Button exit;
    @FXML
    private Button settings;

    @Autowired
    private StagePool stagePool;

    @Autowired
    private Communicator communicator;


    public void initialize() {
        discipline.setItems(FXCollections.observableArrayList("ВВК"));
        discipline.getSelectionModel().selectFirst();

        questionDictionary.getStyleClass().add("icon-button");
        questionDictionary.setPickOnBounds(true);

        Region questionDictionaryIcon = new Region();
        questionDictionaryIcon.getStyleClass().add("questionDictionaryIcon");
        questionDictionary.setGraphic(questionDictionaryIcon);

        labWorks.getStyleClass().add("icon-button");
        labWorks.setPickOnBounds(true);

        Region labWorksIcon = new Region();
        labWorksIcon.getStyleClass().add("labWorksIcon");
        labWorks.setGraphic(labWorksIcon);

        edit.getStyleClass().add("icon-button");
        edit.setPickOnBounds(true);

        Region editIcon = new Region();
        editIcon.getStyleClass().add("editIcon");
        edit.setGraphic(editIcon);

        add.getStyleClass().add("icon-button");
        add.setPickOnBounds(true);

        Region addIcon = new Region();
        addIcon.getStyleClass().add("addIcon");
        add.setGraphic(addIcon);

        exportReport.getStyleClass().add("icon-button");
        exportReport.setPickOnBounds(true);

        Region exportReportIcon = new Region();
        exportReportIcon.getStyleClass().add("exportReportIcon");
        exportReport.setGraphic(exportReportIcon);

        executeLab.getStyleClass().add("icon-button");
        executeLab.setPickOnBounds(true);

        Region executeLabIcon = new Region();
        executeLabIcon.getStyleClass().add("executeLabIcon");
        executeLab.setGraphic(executeLabIcon);

        help.getStyleClass().add("icon-button");
        help.setPickOnBounds(true);

        Region helpIcon = new Region();
        helpIcon.getStyleClass().add("helpIcon");
        help.setGraphic(helpIcon);

        roles.getStyleClass().add("icon-button");
        roles.setPickOnBounds(true);

        Region rolesIcon = new Region();
        rolesIcon.getStyleClass().add("rolesIcon");
        roles.setGraphic(rolesIcon);

        users.getStyleClass().add("icon-button");
        users.setPickOnBounds(true);

        Region usersIcon = new Region();
        usersIcon.getStyleClass().add("usersIcon");
        users.setGraphic(usersIcon);

        exit.getStyleClass().add("icon-button");
        exit.setPickOnBounds(true);

        Region exitIcon = new Region();
        exitIcon.getStyleClass().add("exitIcon");
        exit.setGraphic(exitIcon);

        settings.getStyleClass().add("icon-button");
        settings.setPickOnBounds(true);

        Region settingsIcon = new Region();
        settingsIcon.getStyleClass().add("settingsIcon");
        settings.setGraphic(settingsIcon);

        questionDictionary.setOnAction(event -> {
            QuestionListStage questionListStage = new QuestionListStage((question -> {
            }), true);
            questionListStage.initModality(Modality.APPLICATION_MODAL);
            questionListStage.showAndWait();
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
            Platform.exit();
        });
        settings.setOnAction(event -> {

        });
    }
}