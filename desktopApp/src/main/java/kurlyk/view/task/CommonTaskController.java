package kurlyk.view.task;

import com.google.gson.Gson;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import kurlyk.communication.Communicator;
import kurlyk.models.UserProgress;
import kurlyk.view.common.component.FxDialogs;
import kurlyk.view.common.controller.Controller;
import kurlyk.view.common.controller.TaskBodyController;
import kurlyk.view.common.stage.StagePool;
import kurlyk.view.common.stage.Stages;
import kurlyk.view.createLabWindow.CreateLabSceneCreator;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.util.function.Supplier;

public abstract class CommonTaskController <T> extends Controller implements TaskBodyController<T> {
    @Getter @Setter private Integer atemptsNumber;

    protected void commonConfiguration(
            UserProgress userProgress,
            Supplier<Double> isRightAnswer,
            boolean editable,

            TextArea textArea,
            Button submit,
            Communicator communicator,
            StagePool stagePool
    ) {
        textArea.setEditable(editable);
        if (editable){
            submit.setOnAction(event -> {
                userProgress.getQuestion().setQuestion(textArea.getText());
                userProgress.getQuestion().setAnswer(new Gson().toJson(getResult()));
                try {
                    communicator.postQuestion(userProgress.getQuestion());
                    stagePool.getStage(Stages.CREATE_LAB).setScene(new CreateLabSceneCreator().getScene());
                } catch (IOException e) {
                    FxDialogs.showError("", "Ошибка отправки данных");
                }
            });
        } else{
            atemptsNumber = userProgress.getLabWork().getAtemptsNumber();
            textArea.setText(userProgress.getQuestion().getQuestion());
            submit.setOnAction(event -> {
                userProgress.setScore(isRightAnswer.get());
                modifyButton(submit, userProgress.getScore());
                try {
                    communicator.postUserProgress(userProgress);
                } catch (IOException e) {
                    FxDialogs.showError("", "Ошибка отправки данных");
                }
                FxDialogs.showInformation("Результат", userProgress.getScore() > 0 ? "Верно" : "Неверно");
            });
        }
    }

    private void modifyButton(Button submit, Double score){
        if(score > 0){
            submit.setStyle("-fx-background-color: green");
            submit.setDisable(true);
            atemptsNumber = 0;
            return;
        }
        if (atemptsNumber == null){
            return;
        }
        atemptsNumber--;
        if(atemptsNumber <= 0){
            submit.setStyle("-fx-background-color: red");
            submit.setDisable(true);
            atemptsNumber = 0;
        }
        submit.setText(submit.getText() + " Попыток: " + atemptsNumber);
    }
}
