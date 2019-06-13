package kurlyk.view.common.controller;

import kurlyk.model.Question;
import kurlyk.transfer.ResultAnswerDto;

import java.io.IOException;

public interface TaskBodyController <T> {
    T getResult();
    String getQuestionText();

    ResultAnswerDto getAnswerResult(Integer attempt) throws IOException ;
    Question saveQuestion(Question question) throws IOException;
}
