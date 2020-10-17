package kurlyk.view.executeLabWindow;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import kurlyk.common.Duet;
import kurlyk.communication.Communicator;
import kurlyk.communication.ExecuteMaster;
import kurlyk.communication.UsverInfo;
import kurlyk.model.Question;
import kurlyk.model.UsverProgressQuestion;
import kurlyk.transfer.QuestionIdsDto;
import kurlyk.view.common.controller.Controller;
import kurlyk.view.common.stage.StagePool;
import kurlyk.view.common.stage.base.BaseStage;
import kurlyk.view.components.CommonSceneCreator;
import kurlyk.view.components.QuestionTab;
import kurlyk.view.showAnswerWindow.ShowAnswerStage;
import kurlyk.view.task.SubmitConfigurationController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;


@Component
@Scope("prototype")
public class ExecuteLabController extends Controller {

    @FXML
    private VBox root;
    private TabPane tabPane;
    private BaseStage stage;

    @Autowired
    private StagePool stagePool;

    @Autowired
    private UsverInfo usverInfo;

    @Autowired
    private Communicator communicator;

    @Autowired
    private ExecuteMaster executeMaster;


    public void initialize() {
    }

    public void setTasks() {
        tabPane = new TabPane();
        root.getChildren().add(tabPane);
        //todo где-то тут загрузить прогресс (1)
        try {
            QuestionIdsDto questionIdsDto = communicator.getQuestionsForExecute(executeMaster.getLabWorkId(), executeMaster.getVariant());
            List<UsverProgressQuestion> progress = communicator.getUsverProgressQuestions(executeMaster.getLabWorkId());

            for (UsverProgressQuestion usverProgressQuestion : progress) {
                if (!usverProgressQuestion.isAnswered())
                    continue;
                boolean flag = false;
                if (executeMaster.isTestTime()) {
                    for (Duet<Long, Long> idDuets : questionIdsDto.getTestQuestionIds()){
                        if (idDuets.getB() == usverProgressQuestion.getQuestion().getId()) {
                            flag = true;
                            break;
                        }
                    }
                } else {
                    for (Duet<Long, Long> idDuets : questionIdsDto.getWorkQuestionIds()){
                        if (idDuets.getB() == usverProgressQuestion.getQuestion().getId()) {
                            flag = true;
                            break;
                        }
                    }
                }
                //Создаем вкладки под пройденные вопросы
                if (flag) {
                    Question question = usverProgressQuestion.getQuestion();
                    QuestionTab tab = new QuestionTab("Вопрос №" + (question.getNumber() != null ? question.getNumber() : "КИРПИЧ"), question);
                    Scene scene = CommonSceneCreator.questionSceneCreator(
                            question,
                            true,
                            this::callbackActionBefore,
                            this::callbackActionAfter,
                            null
                    );
                    tab.setContent(
                          scene.getRoot()
                    );
                    tabPane.getTabs().add(tab);
                    tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        initTab();
    }

    private void initTab(){
        //Если пришёл null, что конец тому, что выполняется (лабе или тесту)
        Question question = executeMaster.getQuestion();
        if (question == null) {
            return;
        }
        QuestionTab tab = new QuestionTab("Вопрос №" + (question.getNumber() != null ? question.getNumber() : "КИРПИЧ"), question);
        tab.setContent(
                CommonSceneCreator.questionSceneCreator(
                        question,
                        false,
                        this::callbackActionBefore,
                        this::callbackActionAfter,
                        null
                ).getRoot()
        );
        tabPane.getTabs().add(tab);
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        tabPane.getSelectionModel().select(tab);
    }

    public void setStage(BaseStage stage) {
        this.stage = stage;
        stage.getMainMenu().getShowAnswerItem().setOnAction(event -> {
            try {
                ShowAnswerStage showAnswerStage = new ShowAnswerStage(
                        ((QuestionTab) tabPane.getSelectionModel().getSelectedItem()).getQuestion().getId()
                );
                showAnswerStage.initOwner(this.stage);
                showAnswerStage.initModality(Modality.APPLICATION_MODAL);
                showAnswerStage.showAndWait();
            } catch (Exception ignored) {
            }
        });
    }

    private void callbackActionBefore(Question question){

    }

    private void callbackActionAfter(Question question){
        initTab();
    }
}