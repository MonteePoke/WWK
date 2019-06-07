package kurlyk.view.common.controller;

import kurlyk.transfer.ResultAnswer;

public interface TaskBodyController <T> {

    T getResult();

    ResultAnswer getAnswerResult();
}
