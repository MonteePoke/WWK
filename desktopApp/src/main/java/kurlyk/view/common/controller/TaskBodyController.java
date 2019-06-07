package kurlyk.view.common.controller;

import kurlyk.models.Question;
import kurlyk.transfer.ResultAnswer;

import java.io.IOException;

public interface TaskBodyController <T> {
    T getResult();
    String getQuestionText();

    ResultAnswer getAnswerResult(Integer attempt) throws IOException ;
    Question saveQuestion(Question question) throws IOException;
}
