package kurlyk.communication;


import kurlyk.models.Question;
import kurlyk.transfer.ExecuteCallbackDto;
import kurlyk.transfer.QuestionIdsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Iterator;
import java.util.function.Consumer;


@Component
public class ExecuteMaster {

    @Autowired
    private Communicator communicator;

    private boolean needTest;
    private Long labWorkId;
    private Integer variant;
    private QuestionIdsDto questionIdsDto;
    private Iterator<Long> testQuestionIterator;
    private Iterator<Long> workQuestionIterator;
    private Consumer<ExecuteCallbackDto> testCompleteCallback;
    private Consumer<ExecuteCallbackDto> workCompleteCallback;

    public void initWork(
            Long labWorkId,
            Integer variant,
            Consumer<ExecuteCallbackDto> testCompleteCallback,
            Consumer<ExecuteCallbackDto> workCompleteCallback
    ){
        initWork(labWorkId, variant, workCompleteCallback, testCompleteCallback, true);
    }

    public void initWork(
            Long labWorkId,
            Integer variant,
            Consumer<ExecuteCallbackDto> workCompleteCallback,
            Consumer<ExecuteCallbackDto> testCompleteCallback,
            boolean needTest
    ){
        this.needTest = needTest;
        this.labWorkId = labWorkId;
        this.variant = variant;
        this.workCompleteCallback = workCompleteCallback;
        this.testCompleteCallback = testCompleteCallback;
        try {
            this.questionIdsDto = communicator.getQuestionsForExecute(labWorkId, variant);
            this.testQuestionIterator = questionIdsDto.getTestQuestionIds().iterator();
            this.workQuestionIterator = questionIdsDto.getWorkQuestionIds().iterator();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Question getTestQuestion() throws IOException{
        if(testQuestionIterator.hasNext()){
            return communicator.getQuestionForExecute(testQuestionIterator.next());
        } else {
            //тут спросить базу про результат. Разложить тест и лабу
            testCompleteCallback.accept();
            return null;
        }
    }

    public Question getWorkQuestion() throws IOException{
        if(workQuestionIterator.hasNext()){
            return communicator.getQuestionForExecute(workQuestionIterator.next());
        } else {
            workCompleteCallback.accept();
            return null;
        }
    }
}
