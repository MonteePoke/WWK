package kurlyk.communication;


import kurlyk.common.Duet;
import kurlyk.common.Quartet;
import kurlyk.common.Trio;
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
    private List<UsverProgressQuestion> progress;
    private Iterator<Long> testQuestionIterator;
    private Iterator<Long> workQuestionIterator;
    private Consumer<ExecuteCallbackDto> testCompleteCallback;
    private Consumer<ExecuteCallbackDto> workCompleteCallback;

    private boolean isTestTime;

    public Long getLabWorkId() {
        return labWorkId;
    }

    public Integer getVariant() {
        return variant;
    }

    public boolean isTestTime() {
        return isTestTime;
    }

    public Long getUsverId() {return usverId;}

    public void initWork(
            Long labWorkId,
            Integer variant,
            Consumer<ExecuteCallbackDto> testCompleteCallback,
            Consumer<ExecuteCallbackDto> workCompleteCallback
    ) {
        initWork(labWorkId, variant, testCompleteCallback, workCompleteCallback, true);
    }

    public void initWork(
            Long labWorkId,
            Integer variant,
            Consumer<ExecuteCallbackDto> testCompleteCallback,
            Consumer<ExecuteCallbackDto> workCompleteCallback,
            boolean needTest
    ) {
        this.needTest = needTest;
        this.labWorkId = labWorkId;
        this.usverId = usverInfo.getTokenDto().getUsverId();
        this.variant = variant;
        this.testCompleteCallback = testCompleteCallback;
        this.workCompleteCallback = workCompleteCallback;
        this.isTestTime = true;
        try {
            if (isTestFailed())
                resetResponseReceived(false);
            if (isWorkDone())
                resetResponseReceived(true);
            this.questionIdsDto = communicator.getQuestionsForExecute(labWorkId, variant);
            List<UsverProgressQuestion> progress = communicator.getUsverProgressQuestions(labWorkId, usverId);
            for (UsverProgressQuestion usverProgressQuestion : progress) {
                if (usverProgressQuestion.isAnswered()) {
                    questionIdsDto.deleteTestQuestionId(usverProgressQuestion.getQuestion().getId());
                    questionIdsDto.deleteWorkQuestionId(usverProgressQuestion.getQuestion().getId());
                }
            }
            this.testQuestionIterator = questionIdsDto.getTestQuestionIds().stream().map(Duet::getB).iterator();
            this.workQuestionIterator = questionIdsDto.getWorkQuestionIds().stream().map(Duet::getB).iterator();
            initUsverProgress();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean canSkipTest() {
        return questionIdsDto.getTestQuestionIds().size() <= 0;
    }

    public void skipTest() {
        isTestTime = false;
        ResultDto resultDto = null;
        try {
            resultDto = getResultDto(false);
            testCompleteCallback.accept(
                    ExecuteCallbackDto
                            .builder()
                            .resultDto(resultDto)
                            .isExecuted(resultDto.isExexute())
                            .build()
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean canSkipWork() {
        return questionIdsDto.getWorkQuestionIds().size() <= 0;
    }

    public void skipWork() {
        ResultDto resultDto = null;
        try {
            resultDto = getResultDto(true);
            workCompleteCallback.accept(
                    ExecuteCallbackDto
                            .builder()
                            .resultDto(resultDto)
                            .isExecuted(resultDto.isExexute())
                            .build()
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initUsverProgress() throws IOException {
        LabWork labWork = communicator.getLabWork(labWorkId);
        if (labWork.getInterval() == null) {
            labWork.setInterval(60L);
        }
        Usver usver = communicator.getUsver(usverInfo.getTokenDto().getUsverId());

        //todo Сильно подумоть

        UsverProgressLabWork usverProgressLabWork = UsverProgressLabWork
                .builder()
                .usver(usver)
                .labWork(labWork)
                .startTime(new Date().getTime())
                .endTime(Utils.toDate(LocalDateTime.now().plusMinutes(labWork.getInterval())).getTime())
                .parameters(getUsverProgressLabWorkParameter(labWork))
//                        .usverProgressTasks(getUsverProgressTasks())
                .build();
        Long usverProgressLabWorkId = communicator.saveUsverProgress(usverProgressLabWork);
        usverProgressLabWork.setId(usverProgressLabWorkId);

        //ищем прогресс
        List<UsverProgressQuestion> progressQuestions = communicator.getUsverProgressQuestions(labWorkId, usverId);

        //Собираем вопросы
        List<Duet<Long,Long>> allIds = Utils.joinTwoList(questionIdsDto.getTestQuestionIds(), questionIdsDto.getWorkQuestionIds());

        //Удаляем если прогресс уже есть в бд
        for (UsverProgressQuestion usverProgressQuestion : progressQuestions) {
            allIds.removeIf(o->o.getB().equals(usverProgressQuestion.getQuestion().getId()));
        }

        allIds.forEach(duet -> {
                    UsverProgressQuestion usverProgressQuestion = UsverProgressQuestion
                            .builder()
                            .question(Question.builder().id(duet.getB()).build())
                            .score(0L)
                            .attemptsNumber(0)
                            .responseReceived(false)
                            .usverProgressLabWork(usverProgressLabWork)
                            .build();
                    try {
                        communicator.saveUsverProgressQuestion(usverProgressQuestion);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
        );
    }

    private Set<UsverProgressLabWorkParameter> getUsverProgressLabWorkParameter(LabWork labWork) {
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

    private List<UsverProgressQuestion> getUsverProgressQuestions() throws IOException {
//        Set<UsverProgressQuestion> usverProgressQuestions = new HashSet<>();
        return communicator.getUsverProgressQuestions(labWorkId, usverId);
    }

    public Question getQuestion() {
        Question question = null;
        try {
            if (isTestTime && needTest) {
                question = getTestQuestion();
                if (question == null) {
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

    private void resetResponseReceived(boolean isLabWork) throws IOException {
        List<UsverProgressQuestion> progressQuestions = communicator.getUsverProgressQuestions(labWorkId, usverId);

        if (isLabWork){
            for(UsverProgressQuestion progressQuestion: progressQuestions){
                if (progressQuestion.getQuestion().getTask().getNumber() > 0){
                    //Сбросить ответ
                    progressQuestion.setAttemptsNumber(0);
                    progressQuestion.setScore(0L);
                    progressQuestion.setResponseReceived(false);
                    communicator.saveUsverProgressQuestion(progressQuestion);
                }
            }

        } else {
            for(UsverProgressQuestion progressQuestion: progressQuestions){
                if (progressQuestion.getQuestion().getTask().getNumber() == 0){
                    //Сбросить ответ
                    progressQuestion.setAttemptsNumber(0);
                    progressQuestion.setScore(0L);
                    progressQuestion.setResponseReceived(false);
                    communicator.saveUsverProgressQuestion(progressQuestion);
                }
            }
        }

    }

    private Question getTestQuestion() throws IOException {
        if (testQuestionIterator.hasNext()) {
            if (isTestFailed())
                System.out.println("Проиграл");

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

    private Question getWorkQuestion() throws IOException {
        if (workQuestionIterator.hasNext()) {
            return communicator.getQuestionForExecute(workQuestionIterator.next());
        } else {
            ResultDto resultDto = getResultDto(true);

            if (!isWorkDone()){
                // сброс ответа на вопрос
                resetResponseReceived(true);
            }

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

    private ResultDto getResultDto(boolean forWork) throws IOException {
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

    //Возвращает Очки/Макс очки/Попытки/Кол-во вопросов
    public Quartet<Long, Long, Long, Long> getScoreForTest() {
        Long maxScore = 0L;
        Long score = 0L;
        Long attemps = 0L;
        List<Duet<Long,Long>> duets = null;
        try {
            QuestionIdsDto questionIdsDto = communicator.getQuestionsForExecute(labWorkId, variant);
            List<UsverProgressQuestion> usverProgressQuestions = communicator.getUsverProgressQuestions(labWorkId, usverId);
            duets = questionIdsDto.getTestQuestionIds();
            //Считаем максимальный и просто балл
            for (Duet<Long, Long> duet : duets) {
                for (UsverProgressQuestion usverProgressQuestion : usverProgressQuestions) {
                    if (duet.getB().equals(usverProgressQuestion.getQuestion().getId())) {
                        score += usverProgressQuestion.getScore();
                        maxScore += usverProgressQuestion.getQuestion().getScore();
                        attemps += usverProgressQuestion.getAttemptsNumber();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Quartet<Long,Long,Long,Long>(score,maxScore, new Long(duets.size()), attemps);
    }

    //Возвращает Очки/Макс очки/Попытки/Кол-во вопросов
    public Quartet<Long, Long, Long, Long> getScoreForWork() {
        Long maxScore = 0L;
        Long score = 0L;
        Long attemps = 0L;
        List<Duet<Long,Long>> duets = null;
        try {
            QuestionIdsDto questionIdsDto = communicator.getQuestionsForExecute(labWorkId, variant);
            List<UsverProgressQuestion> usverProgressQuestions = communicator.getUsverProgressQuestions(labWorkId, usverId);
            duets = questionIdsDto.getWorkQuestionIds();
            //Считаем максимальный и просто балл
            for (Duet<Long, Long> duet : duets) {
                for (UsverProgressQuestion usverProgressQuestion : usverProgressQuestions) {
                    if (duet.getB().equals(usverProgressQuestion.getQuestion().getId())) {
                        score += usverProgressQuestion.getScore();
                        maxScore += usverProgressQuestion.getQuestion().getScore();
                        attemps += usverProgressQuestion.getAttemptsNumber();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Quartet<Long,Long,Long,Long>(score,maxScore, new Long(duets.size()), attemps);
    }

    public boolean isTestFailed() {
        Long maxScore = 0L;
        Long failedScore = 0L;
        List<Duet<Long,Long>> duets = null;
        try {
            List<UsverProgressQuestion> usverProgressQuestions = communicator.getUsverProgressQuestions(labWorkId, usverId);
            duets = communicator.getQuestionsForExecute(labWorkId, variant).getTestQuestionIds();
            for (Duet<Long, Long> duet : duets) {
                for (UsverProgressQuestion usverProgressQuestion : usverProgressQuestions) {
                    if (duet.getB().equals(usverProgressQuestion.getQuestion().getId())) {
                        if (usverProgressQuestion.isAnswered() == true)
                            failedScore += usverProgressQuestion.getQuestion().getScore() - usverProgressQuestion.getScore();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return failedScore > maxScore/2;
    }

    public boolean isWorkFailed() {
        Long maxScore = 0L;
        Long failedScore = 0L;
        List<Duet<Long,Long>> duets = null;
        try {
            List<UsverProgressQuestion> usverProgressQuestions = communicator.getUsverProgressQuestions(labWorkId, usverId);
            duets = communicator.getQuestionsForExecute(labWorkId, variant).getWorkQuestionIds();
            for (Duet<Long, Long> duet : duets) {
                for (UsverProgressQuestion usverProgressQuestion : usverProgressQuestions) {
                    if (duet.getB().equals(usverProgressQuestion.getQuestion().getId())) {
                        if (usverProgressQuestion.isAnswered() == true)
                            failedScore += usverProgressQuestion.getQuestion().getScore() - usverProgressQuestion.getScore();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return failedScore > maxScore/2;
    }

    public boolean isTestDone() {
        Quartet<Long, Long, Long, Long> quartet = getScoreForTest();
        return quartet.getA()*2 >= quartet.getB();
    }

    public boolean isWorkDone() {
        Quartet<Long, Long, Long, Long> quartet = getScoreForWork();
        return quartet.getA()*2 >= quartet.getB();
    }
}
