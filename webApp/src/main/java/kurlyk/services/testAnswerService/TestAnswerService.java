package kurlyk.services.testAnswerService;

import kurlyk.transfer.ResultAnswerDto;
import kurlyk.transfer.answer.*;


public interface TestAnswerService {

    ResultAnswerDto testComputerSystemAnswer(ComputerSystemAnswerDto dto);
    ResultAnswerDto testFormulaAnswer(FormulaAnswerDto dto);
    ResultAnswerDto testTextAnswer(TextAnswerDto dto);
    ResultAnswerDto testNumberAnswer(NumberAnswerDto dto);
    ResultAnswerDto testMatchingAnswer(MatchingAnswerDto dto);
    ResultAnswerDto testCheckAnswer(SelectAnswerDto dto);
    ResultAnswerDto testRadioAnswer(SelectAnswerDto dto);
    ResultAnswerDto testSortingAnswer(SortingAnswerDto dto);
}
