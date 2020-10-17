package kurlyk.view.task;

import com.google.gson.Gson;
import javafx.scene.control.Button;
import kurlyk.communication.Communicator;
import kurlyk.model.Question;
import kurlyk.transfer.ResultAnswerDto;
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
    @Getter @Setter private Stages stageForClose;
    private boolean callbackIsExecuted = false;
    private Button submitButton = null;

    protected void submitConfiguration(
            boolean editable,
            Question question,
            Button submit,
            Communicator communicator,
            StagePool stagePool,
            Consumer<Question> callbackBefore,
            Consumer<Question> callbackAfter
    ) {
        this.question = question;
        this.communicator = communicator;
        this.submitButton = submit;
        if (editable) {
            blockButton();
            submit.setOnAction(event -> {
                try {
                    callbackBefore.accept(this.question);
                    Question savedQuestion = saveQuestion(this.question);
                    callbackAfter.accept(savedQuestion);
                    if (stageForClose != null) {
                        stagePool.closeStage(stageForClose);
                    }
                } catch (IOException e) {
                    FxDialogs.showError("", "Ошибка отправки данных");
                }
            });
        } else {
            attemptsNumber = this.question.getAttemptsNumber();
            // Событие на нажатие кнопку ответа
            submit.setOnAction(event -> {
                modifyButton();
                try {
                    callbackBefore.accept(this.question);
                    ResultAnswerDto resultAnswerDto = getAnswerResult(this.question.getAttemptsNumber() - attemptsNumber);
                    if (!callbackIsExecuted) {
                        callbackAfter.accept(this.question);
                        callbackIsExecuted = true;
                    }
                    if (stageForClose != null) {
                        stagePool.closeStage(stageForClose);
                    }
                } catch (IOException e) {
                    FxDialogs.showError("", "Ошибка отправки данных");
                }
            });
        }
    }

    // Блочит кнопку если номер попытки закончились
    private void modifyButton() {
        attemptsNumber--;
        if (attemptsNumber <= 0) {
            blockButton();
            attemptsNumber = 0;
        }
    }

    public void blockButton() {
        submitButton.setStyle("-fx-background-color: green");
        submitButton.setDisable(true);
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
