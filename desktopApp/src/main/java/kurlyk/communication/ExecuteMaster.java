package kurlyk.communication;


import kurlyk.model.*;
import kurlyk.transfer.ExecuteCallbackDto;
import kurlyk.transfer.QuestionIdsDto;
import kurlyk.transfer.ResultDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;


@Component
public class ExecuteMaster {

    @Autowired
    private Communicator communicator;

    @Autowired
    private UsverInfo usverInfo;

    private boolean needTest;
    private Long labWorkId;
    private Long usverId;
    private Integer variant;
    private QuestionIdsDto questionIdsDto;
    private Iterator<Long> testQuestionIterator;
    private Iterator<Long> workQuestionIterator;
    private Consumer<ExecuteCallbackDto> testCompleteCallback;
    private Consumer<ExecuteCallbackDto> workCompleteCallback;

    private boolean isTestTime = true;

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
        this.usverId = usverInfo.getTokenDto().getUsverId();
        this.variant = variant;
        this.workCompleteCallback = workCompleteCallback;
        this.testCompleteCallback = testCompleteCallback;
        try {
            this.questionIdsDto = communicator.getQuestionsForExecute(labWorkId, variant);
            this.testQuestionIterator = questionIdsDto.getTestQuestionIds().iterator();
            this.workQuestionIterator = questionIdsDto.getWorkQuestionIds().iterator();
            initUsverProgress();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initUsverProgress() throws IOException{
        LabWork labWork = communicator.getLabWork(labWorkId);
        Usver usver = communicator.getUsver(usverInfo.getTokenDto().getUsverId());

        Integer difficultyLevelsNumber = labWork.getDifficultyLevelsNumber() != null ? labWork.getDifficultyLevelsNumber() : 1;
        Integer variantsNumber = labWork.getVariantsNumber() != null ? labWork.getVariantsNumber() : 1;
        labWork.getParameterValues()
                .stream()
                .map(parameterValue -> {
                    parameterValue.getValueTo() - parameterValue.getValueFrom()
                })
        communicator.saveUsverProgress(
                UsverProgressLabWork
                        .builder()
                        .usver(usver)
                        .labWork(labWork)
                        .startTime(LocalDateTime.now())
                        .endTime(LocalDateTime.now().plus(labWork.getInterval()))
                        .parameters()
                        .usverProgressTasks()
                        .build()
        )
    }

    public Question getQuestion(){
        Question question = null;
        try {
            if(isTestTime && needTest){
                question = getTestQuestion();
                if(question == null){
                    isTestTime = false;
                }
            } else {
                question = getWorkQuestion();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return question;
    }

    private Question getTestQuestion() throws IOException{
        if(testQuestionIterator.hasNext()){
            return communicator.getQuestionForExecute(testQuestionIterator.next());
        } else {
            ResultDto resultDto = getResultDto(false);
            testCompleteCallback.accept(
                    ExecuteCallbackDto
                            .builder()
                            .resultDto(resultDto)
                            .isExecuted(resultDto.isExexute())
                            .build()
            );
            return null;
        }
    }

    private Question getWorkQuestion() throws IOException{
        if(workQuestionIterator.hasNext()){
            return communicator.getQuestionForExecute(workQuestionIterator.next());
        } else {
            ResultDto resultDto = getResultDto(true);
            workCompleteCallback.accept(
                    ExecuteCallbackDto
                            .builder()
                            .resultDto(resultDto)
                            .isExecuted(true)
                            .build()
            );
            return null;
        }
    }

    private ResultDto getResultDto(boolean forWork) throws IOException{
        List<UsverProgressQuestion> usverProgressQuestions = communicator.getStatiscticByUsverIdByLabWorkId(usverId, labWorkId).getUsverProgressTasks()
                .stream()
                .filter(usverProgressTask -> usverProgressTask.getTask().isTest() ^ forWork)
                .flatMap(usverProgressTask -> usverProgressTask.getUsverProgressQuestions().stream())
                .collect(Collectors.toList());

        return ResultDto
                .builder()
                .score(usverProgressQuestions.stream().mapToLong(UsverProgressQuestion::getScore).sum())
                .maxScore(usverProgressQuestions.stream().mapToLong(usverProgressQuestion -> usverProgressQuestion.getQuestion().getScore()).sum())
                .questionsNumber(usverProgressQuestions.size())
                .attemptsNumber(usverProgressQuestions.stream().mapToInt(UsverProgressQuestion::getAttemptsNumber).sum())
                .type(forWork ? ResultDto.Type.LAB_WORK : ResultDto.Type.TEST)
                .build();
    }
}
