package kurlyk.services.testAnswerService;

import kurlyk.transfer.ResultAnswer;
import kurlyk.transfer.answer.*;


public interface TestAnswerService {

    ResultAnswer testComputerSystemAnswer(ComputerSystemAnswerDto dto);
    ResultAnswer testFormulaAnswer(FormulaAnswerDto dto);
    ResultAnswer testTextAnswer(TextAnswerDto dto);
    ResultAnswer testNumberAnswer(NumberAnswerDto dto);
    ResultAnswer testMatchingAnswer(MatchingAnswerDto dto);
    ResultAnswer testCheckAnswer(SelectAnswerDto dto);
    ResultAnswer testRadioAnswer(SelectAnswerDto dto);
}
