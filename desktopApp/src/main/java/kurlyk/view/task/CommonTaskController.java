package kurlyk.view.task;

import com.google.gson.Gson;
import javafx.scene.control.Button;
import kurlyk.communication.Communicator;
import kurlyk.models.UserProgress;
import kurlyk.view.common.component.FxDialogs;
import kurlyk.view.common.component.MyHTMLEditor;
import kurlyk.view.common.controller.Controller;
import kurlyk.view.common.controller.TaskBodyController;
import kurlyk.view.common.stage.StagePool;
import kurlyk.view.common.stage.Stages;
import kurlyk.view.create.createQuestionWindow.CreateQuestionSceneCreator;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.util.function.Supplier;

public abstract class CommonTaskController <T> extends Controller implements TaskBodyController<T> {
    @Getter @Setter private Integer atemptsNumber;
    @Getter @Setter private int errorsNumber = 0;

    protected void commonConfiguration(
            UserProgress userProgress,
            Supplier<Double> isRightAnswer,
            boolean editable,

            MyHTMLEditor textArea,
            Button submit,
            Communicator communicator,
            StagePool stagePool
    ) {
        textArea.setDisable(!editable);
        textArea.setMinHeight(200);
        if (editable){
            submit.setOnAction(event -> {
                userProgress.getQuestion().setQuestion(textArea.getHtmlText());
                userProgress.getQuestion().setAnswer(new Gson().toJson(getResult()));
                try {
                    communicator.postQuestion(userProgress.getQuestion());
                    stagePool.getStage(Stages.COMMON_CREATE).setScene(new CreateQuestionSceneCreator().getScene());
                } catch (IOException e) {
                    FxDialogs.showError("", "Ошибка отправки данных");
                }
            });
        } else{
            atemptsNumber = userProgress.getLabWork().getAtemptsNumber();
            textArea.setHtmlText(userProgress.getQuestion().getQuestion());
            submit.setOnAction(event -> {
                userProgress.setScore(isRightAnswer.get());
                modifyButton(submit, userProgress.getScore());
                userProgress.setErrorsNumber(errorsNumber);
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
        errorsNumber++;
        if (atemptsNumber == null){
            return;
        }
        atemptsNumber--;
        if(atemptsNumber <= 0){
            submit.setStyle("-fx-background-color: red");
            submit.setDisable(true);
            atemptsNumber = 0;
        }
        submit.setText(getNameButton());
    }

    private String getNameButton(){
        if (atemptsNumber == null){
            return "Ответ";
        } else {
            return "Ответ. Осталось попыток: " + atemptsNumber;
        }
    }
}
