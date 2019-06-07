package kurlyk.view.task;

import com.google.gson.Gson;
import javafx.scene.control.Button;
import kurlyk.communication.Communicator;
import kurlyk.models.Question;
import kurlyk.transfer.ResultAnswer;
import kurlyk.view.common.controller.Controller;
import kurlyk.view.common.controller.TaskBodyController;
import kurlyk.view.common.stage.StagePool;
import kurlyk.view.common.stage.Stages;
import kurlyk.view.components.FxDialogs;
import kurlyk.view.components.MyHtmlEditor;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.util.function.Consumer;
import java.util.function.Supplier;

public abstract class CommonTaskController<T> extends Controller implements TaskBodyController<T> {
    @Getter
    @Setter
    private Integer attemptsNumber;
    @Getter
    @Setter
    private int errorsNumber = 0;

    protected void commonConfiguration(
            Supplier<ResultAnswer> getAnswerResult,
            boolean editable,
            MyHtmlEditor textArea,
            Button submit,
            Communicator communicator,
            StagePool stagePool,
            Consumer<Question> callback
    ) {
        textArea.setDisable(!editable);
        textArea.setMinHeight(200);
        if (editable) {
            submit.setOnAction(event -> {
                userProgress.getQuestion().setQuestion(textArea.getHtmlText());
                userProgress.getQuestion().setAnswer(new Gson().toJson(getResult()));
                try {
                    communicator.saveQuestion(userProgress.getQuestion());
                    callback.accept(userProgress.getQuestion());
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
            attemptsNumber = userProgress.getLabWork().getAttemptsNumber();
            textArea.setHtmlText(userProgress.getQuestion().getQuestion());
            submit.setOnAction(event -> {
                userProgress.setScore(isRightAnswer.get());
                modifyButton(submit, userProgress.getScore());
                userProgress.setErrorsNumber(errorsNumber);
                try {
                    communicator.saveUserProgress(userProgress);
                } catch (IOException e) {
                    FxDialogs.showError("", "Ошибка отправки данных");
                }
                FxDialogs.showInformation("Результат", userProgress.getScore() > 0 ? "Верно" : "Неверно");
            });
        }
    }

    private void modifyButton(Button submit, Double score) {
        if (score > 0) {
            submit.setStyle("-fx-background-color: green");
            submit.setDisable(true);
            attemptsNumber = 0;
            return;
        }
        errorsNumber++;
        if (attemptsNumber == null) {
            return;
        }
        attemptsNumber--;
        if (attemptsNumber <= 0) {
            submit.setStyle("-fx-background-color: red");
            submit.setDisable(true);
            attemptsNumber = 0;
        }
        submit.setText(getNameButton());
    }

    private String getNameButton() {
        if (attemptsNumber == null) {
            return "Ответ";
        } else {
            return "Ответ. Осталось попыток: " + attemptsNumber;
        }
    }
}
