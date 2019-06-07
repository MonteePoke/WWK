package kurlyk.view.task;

import com.google.gson.Gson;
import javafx.scene.control.Button;
import kurlyk.communication.Communicator;
import kurlyk.models.Question;
import kurlyk.view.common.controller.Controller;
import kurlyk.view.common.controller.TaskBodyController;
import kurlyk.view.common.stage.StagePool;
import kurlyk.view.common.stage.Stages;
import kurlyk.view.components.FxDialogs;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.util.function.Consumer;

public abstract class SubmitConfigurationController<T> extends Controller implements TaskBodyController<T> {
    @Getter @Setter private Integer attemptsNumber = 1;

    @Getter @Setter private Question question;
    @Getter @Setter private Communicator communicator;

    protected void submitConfiguration(
            boolean editable,
            Question question,
            Button submit,
            Communicator communicator,
            StagePool stagePool,
            Consumer<Question> callback
    ) {
        this.question = question;
        this.communicator = communicator;
        if (editable) {
            submit.setOnAction(event -> {
                try {
                    callback.accept(saveQuestion(this.question));
                    try {
                        stagePool.showStage(Stages.COMMON_CREATE);
                    } catch (Exception e) {
                    }
                    stagePool.closeStage(Stages.CREATE_QUESTION);
                } catch (IOException e) {
                    FxDialogs.showError("", "Ошибка отправки данных");
                }
            });
        } else {
            attemptsNumber = this.question.getAttemptsNumber();
            submit.setOnAction(event -> {
                modifyButton(submit);
                try {
                    getAnswerResult(this.question.getAttemptsNumber() - attemptsNumber);
                } catch (IOException e) {
                    FxDialogs.showError("", "Ошибка отправки данных");
                }
            });
        }
    }

    private void modifyButton(Button submit) {
        attemptsNumber--;
        if (attemptsNumber <= 0) {
            submit.setStyle("-fx-background-color: green");
            submit.setDisable(true);
            attemptsNumber = 0;
        }
    }


    @Override
    public Question saveQuestion(Question question) throws IOException {
        question.setQuestion(getQuestionText());
        question.setAnswer(new Gson().toJson(getResult()));
        Long id = communicator.saveQuestion(question);
        question.setId(id);
        return question;
    }
}
