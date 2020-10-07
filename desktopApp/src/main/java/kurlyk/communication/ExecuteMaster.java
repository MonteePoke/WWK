package kurlyk.communication;


import kurlyk.common.Duet;
import kurlyk.common.Utils;
import kurlyk.model.*;
import kurlyk.transfer.ExecuteCallbackDto;
import kurlyk.transfer.QuestionIdsDto;
import kurlyk.transfer.ResultDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;
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

    private boolean isTestTime;

    public void initWork(
            Long labWorkId,
            Integer variant,
            Consumer<ExecuteCallbackDto> testCompleteCallback,
            Consumer<ExecuteCallbackDto> workCompleteCallback
    ){
        initWork(labWorkId, variant, testCompleteCallback, workCompleteCallback, true);
    }

    public void initWork(
            Long labWorkId,
            Integer variant,
            Consumer<ExecuteCallbackDto> testCompleteCallback,
            Consumer<ExecuteCallbackDto> workCompleteCallback,
            boolean needTest
    ){
        this.needTest = needTest;
        this.labWorkId = labWorkId;
        this.usverId = usverInfo.getTokenDto().getUsverId();
        this.variant = variant;
        this.testCompleteCallback = testCompleteCallback;
        this.workCompleteCallback = workCompleteCallback;
        this.isTestTime = true;
        try {
            this.questionIdsDto = communicator.getQuestionsForExecute(labWorkId, variant);
            this.testQuestionIterator = questionIdsDto.getTestQuestionIds().stream().map(Duet::getB).iterator();
            this.workQuestionIterator = questionIdsDto.getWorkQuestionIds().stream().map(Duet::getB).iterator();
            initUsverProgress();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initUsverProgress() throws IOException{
        LabWork labWork = communicator.getLabWork(labWorkId);
        if(labWork.getInterval() == null){
            labWork.setInterval(60L);
        }
        Usver usver = communicator.getUsver(usverInfo.getTokenDto().getUsverId());

        /**
         * ОСТАНОВИЛИСЬ
         * ТУТ
         * ПОДУМОТЬ
         * ОЧЕНЬ
         * СИЛЬНО
         * **/
        Set<UsverProgressQuestion> usverProgressQuestions = new HashSet<>();

        Map<Long, List<UsverProgressQuestion>> questionMap = Utils.joinTwoList(questionIdsDto.getTestQuestionIds(), questionIdsDto.getWorkQuestionIds())
                .stream()
                .map(duet ->
                        UsverProgressQuestion
                                .builder()
                                .question(Question.builder().id(duet.getB()).task(Task.builder().id(duet.getA()).build()).build())
                                .score(0L)
                                .attemptsNumber(0)
                                .build()
                )
                .collect(Collectors.groupingBy(usverProgressQuestion -> usverProgressQuestion.getQuestion().getTask().getId()));

        communicator.saveUsverProgress(
                UsverProgressLabWork
                        .builder()
                        .usver(usver)
                        .labWork(labWork)
                        .startTime(new Date().getTime())
                        .endTime(Utils.toDate(LocalDateTime.now().plusMinutes(labWork.getInterval())).getTime())
                        .parameters(getUsverProgressLabWorkParameter(labWork))
//                        .usverProgressTasks(getUsverProgressTasks())
                        .build()
        );
    }

    private Set<UsverProgressLabWorkParameter> getUsverProgressLabWorkParameter(LabWork labWork){
        int difficultyLevelsNumber = labWork.getDifficultyLevelsNumber() != null ? labWork.getDifficultyLevelsNumber() : 1;
        int variantsNumber = labWork.getVariantsNumber() != null ? labWork.getVariantsNumber() : 1;
        final int numberOfParts = ((variantsNumber / difficultyLevelsNumber) > 0) ? (variantsNumber / difficultyLevelsNumber) : 1;

        return labWork.getParameterValues()
                .stream()
                .map(parameterValue -> {
                    List<Double> values = Utils.getRandomNumbersFromRanges(
                            parameterValue.getValueTo(),
                            parameterValue.getValueFrom(),
                            numberOfParts
                    );
                    return UsverProgressLabWorkParameter
                            .builder()
                            .parameterName(parameterValue.getParameterName())
                            .parameterSysName(parameterValue.getParameterSysName())
                            .parameterValue(values.get(numberOfParts - 1))
                            .build();

                })
                .collect(Collectors.toSet());
    }

//    private Set<UsverProgressTask> getUsverProgressTasks(){
//        Set<UsverProgressTask> usverProgressTasks = new HashSet<>();
//
//        Map<Long, List<UsverProgressQuestion>> taskMap = Utils.joinTwoList(questionIdsDto.getTestQuestionIds(), questionIdsDto.getWorkQuestionIds())
//                .stream()
//                .map(duet ->
//                    UsverProgressQuestion
//                            .builder()
//                            .question(Question.builder().id(duet.getB()).task(Task.builder().id(duet.getA()).build()).build())
//                            .score(0L)
//                            .attemptsNumber(0)
//                            .build()
//                )
//                .collect(Collectors.groupingBy(usverProgressQuestion -> usverProgressQuestion.getQuestion().getTask().getId()));
//        for (Long taskId : taskMap.keySet()){
//            usverProgressTasks.add(
//                    UsverProgressTask
//                            .builder()
//                            .task(Task.builder().id(taskId).build())
//                            .usverProgressQuestions(new HashSet<>(taskMap.get(taskId)))
//                            .build()
//            );
//        }
//        return usverProgressTasks;
//    }
    
    private List<UsverProgressQuestion> getUsverProgressQuestions() throws IOException {
//        Set<UsverProgressQuestion> usverProgressQuestions = new HashSet<>();

        return communicator.getUsverProgressQuestions(labWorkId);
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
                            .isExecuted(resultDto.isExexute())
                            .build()
            );
            return null;
        }
    }

    private ResultDto getResultDto(boolean forWork) throws IOException{
//        List<UsverProgressQuestion> usverProgressQuestions = communicator.getStatiscticByUsverIdByLabWorkId(usverId, labWorkId)
//                .usverProgressTask.getUsverProgressQuestions()
//                .stream()
////                .filter(usverProgressTask -> usverProgressTask.getTask().isTest() ^ forWork)
////                .flatMap(usverProgressTask -> usverProgressTask.getUsverProgressQuestions().stream())
//                .collect(Collectors.toList());

        List<UsverProgressQuestion> usverProgressQuestions = getUsverProgressQuestions();

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
