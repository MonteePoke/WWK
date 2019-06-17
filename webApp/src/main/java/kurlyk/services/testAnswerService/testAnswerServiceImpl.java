package kurlyk.services.testAnswerService;

import com.google.gson.Gson;
import kurlyk.common.Duet;
import kurlyk.common.Trio;
import kurlyk.common.Utils;
import kurlyk.common.algorithm.StemmerPorterRU;
import kurlyk.common.algorithm.formulaTester.FormulaTester;
import kurlyk.common.algorithm.graph.ComputerSystem.ComputerSystemElementType;
import kurlyk.common.algorithm.graph.ComputerSystem.SimpleComputerSystemElement;
import kurlyk.common.algorithm.graph.SimpleGraphSystem;
import kurlyk.model.Question;
import kurlyk.model.UsverProgressLabWork;
import kurlyk.services.labWork.LabWorkService;
import kurlyk.services.question.QuestionService;
import kurlyk.services.task.TaskService;
import kurlyk.services.usver.UsverService;
import kurlyk.services.usverProgress.UsverProgressService;
import kurlyk.transfer.ResultAnswerDto;
import kurlyk.transfer.answer.*;
import kurlyk.transfer.tasks.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class testAnswerServiceImpl implements TestAnswerService {

    @Autowired
    private LabWorkService labWorkService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private QuestionService questionService;

    @Autowired
    private UsverService usverService;

    @Autowired
    private UsverProgressService usverProgressService;

    public ResultAnswerDto testComputerSystemAnswer(@RequestBody ComputerSystemAnswerDto dto){
        Optional<Question> optionalQuestion = questionService.getQuestion(dto.getQuestionId());
        ResultAnswerDto resultAnswerDto = new ResultAnswerDto();
        if(optionalQuestion.isPresent()){
            ComputerSystemDto standart = new Gson().fromJson(optionalQuestion.get().getAnswer(), ComputerSystemDto.class);

            resultAnswerDto.setQuestionId(optionalQuestion.get().getId());
            resultAnswerDto.setTaskId(optionalQuestion.get().getTask().getId());
            resultAnswerDto.setLabWorkId(optionalQuestion.get().getTask().getLabWork().getId());
            resultAnswerDto.setUsverId(dto.getUsverId());
            setScoreToResult(resultAnswerDto, testComputerSystemDto(standart, dto.getEntity()), dto.getAttemptsNumber());

            saveResult(resultAnswerDto);
        } else {
            resultAnswerDto.setQuestionNotFound(true);
        }
        return resultAnswerDto;
    }

    public ResultAnswerDto testFormulaAnswer(FormulaAnswerDto dto){
        Optional<Question> optionalQuestion = questionService.getQuestion(dto.getQuestionId());
        ResultAnswerDto resultAnswerDto = new ResultAnswerDto();
        if(optionalQuestion.isPresent()){
            FormulaDto standart = new Gson().fromJson(optionalQuestion.get().getAnswer(), FormulaDto.class);

            resultAnswerDto.setQuestionId(optionalQuestion.get().getId());
            resultAnswerDto.setTaskId(optionalQuestion.get().getTask().getId());
            resultAnswerDto.setLabWorkId(optionalQuestion.get().getTask().getLabWork().getId());
            resultAnswerDto.setUsverId(dto.getUsverId());
            setScoreToResult(resultAnswerDto, testFormulaDto(standart, dto.getEntity()), dto.getAttemptsNumber());

            saveResult(resultAnswerDto);
        } else {
            resultAnswerDto.setQuestionNotFound(true);
        }
        return resultAnswerDto;
    }

    public ResultAnswerDto testTextAnswer(TextAnswerDto dto){
        Optional<Question> optionalQuestion = questionService.getQuestion(dto.getQuestionId());
        ResultAnswerDto resultAnswerDto = new ResultAnswerDto();
        if(optionalQuestion.isPresent()){
            TextDto standart = new Gson().fromJson(optionalQuestion.get().getAnswer(), TextDto.class);

            resultAnswerDto.setQuestionId(optionalQuestion.get().getId());
            resultAnswerDto.setTaskId(optionalQuestion.get().getTask().getId());
            resultAnswerDto.setLabWorkId(optionalQuestion.get().getTask().getLabWork().getId());
            resultAnswerDto.setUsverId(dto.getUsverId());
            setScoreToResult(resultAnswerDto, testTextDto(standart, dto.getEntity()), dto.getAttemptsNumber());

            saveResult(resultAnswerDto);
        } else {
            resultAnswerDto.setQuestionNotFound(true);
        }
        return resultAnswerDto;
    }

    public ResultAnswerDto testNumberAnswer(NumberAnswerDto dto){
        Optional<Question> optionalQuestion = questionService.getQuestion(dto.getQuestionId());
        ResultAnswerDto resultAnswerDto = new ResultAnswerDto();
        if(optionalQuestion.isPresent()){
            NumberDto standart = new Gson().fromJson(optionalQuestion.get().getAnswer(), NumberDto.class);

            resultAnswerDto.setQuestionId(optionalQuestion.get().getId());
            resultAnswerDto.setTaskId(optionalQuestion.get().getTask().getId());
            resultAnswerDto.setLabWorkId(optionalQuestion.get().getTask().getLabWork().getId());
            resultAnswerDto.setUsverId(dto.getUsverId());
            setScoreToResult(resultAnswerDto, testNumberDto(standart, dto.getEntity()), dto.getAttemptsNumber());

            saveResult(resultAnswerDto);
        } else {
            resultAnswerDto.setQuestionNotFound(true);
        }
        return resultAnswerDto;
    }

    public ResultAnswerDto testMatchingAnswer(MatchingAnswerDto dto){
        Optional<Question> optionalQuestion = questionService.getQuestion(dto.getQuestionId());
        ResultAnswerDto resultAnswerDto = new ResultAnswerDto();
        if(optionalQuestion.isPresent()){
            MatchingDto standart = new Gson().fromJson(optionalQuestion.get().getAnswer(), MatchingDto.class);

            resultAnswerDto.setQuestionId(optionalQuestion.get().getId());
            resultAnswerDto.setTaskId(optionalQuestion.get().getTask().getId());
            resultAnswerDto.setLabWorkId(optionalQuestion.get().getTask().getLabWork().getId());
            resultAnswerDto.setUsverId(dto.getUsverId());
            setScoreToResult(resultAnswerDto, testMatchingDto(standart, dto.getEntity()), dto.getAttemptsNumber());

            saveResult(resultAnswerDto);
        } else {
            resultAnswerDto.setQuestionNotFound(true);
        }
        return resultAnswerDto;
    }

    public ResultAnswerDto testCheckAnswer(SelectAnswerDto dto){
        Optional<Question> optionalQuestion = questionService.getQuestion(dto.getQuestionId());
        ResultAnswerDto resultAnswerDto = new ResultAnswerDto();
        if(optionalQuestion.isPresent()){
            SelectDto standart = new Gson().fromJson(optionalQuestion.get().getAnswer(), SelectDto.class);

            resultAnswerDto.setQuestionId(optionalQuestion.get().getId());
            resultAnswerDto.setTaskId(optionalQuestion.get().getTask().getId());
            resultAnswerDto.setLabWorkId(optionalQuestion.get().getTask().getLabWork().getId());
            resultAnswerDto.setUsverId(dto.getUsverId());
            setScoreToResult(resultAnswerDto, testCheckDto(standart, dto.getEntity()), dto.getAttemptsNumber());

            saveResult(resultAnswerDto);
        } else {
            resultAnswerDto.setQuestionNotFound(true);
        }
        return resultAnswerDto;
    }

    public ResultAnswerDto testRadioAnswer(SelectAnswerDto dto){
        Optional<Question> optionalQuestion = questionService.getQuestion(dto.getQuestionId());
        ResultAnswerDto resultAnswerDto = new ResultAnswerDto();
        if(optionalQuestion.isPresent()){
            SelectDto standart = new Gson().fromJson(optionalQuestion.get().getAnswer(), SelectDto.class);

            resultAnswerDto.setQuestionId(optionalQuestion.get().getId());
            resultAnswerDto.setTaskId(optionalQuestion.get().getTask().getId());
            resultAnswerDto.setLabWorkId(optionalQuestion.get().getTask().getLabWork().getId());
            resultAnswerDto.setUsverId(dto.getUsverId());
            setScoreToResult(resultAnswerDto, testRadioDto(standart, dto.getEntity()), dto.getAttemptsNumber());

            saveResult(resultAnswerDto);
        } else {
            resultAnswerDto.setQuestionNotFound(true);
        }
        return resultAnswerDto;
    }

    public ResultAnswerDto testSortingAnswer(SortingAnswerDto dto){
        Optional<Question> optionalQuestion = questionService.getQuestion(dto.getQuestionId());
        ResultAnswerDto resultAnswerDto = new ResultAnswerDto();
        if(optionalQuestion.isPresent()){
            SortingDto standart = new Gson().fromJson(optionalQuestion.get().getAnswer(), SortingDto.class);

            resultAnswerDto.setQuestionId(optionalQuestion.get().getId());
            resultAnswerDto.setTaskId(optionalQuestion.get().getTask().getId());
            resultAnswerDto.setLabWorkId(optionalQuestion.get().getTask().getLabWork().getId());
            resultAnswerDto.setUsverId(dto.getUsverId());
            setScoreToResult(resultAnswerDto, testSortingDto(standart, dto.getEntity()), dto.getAttemptsNumber());

            saveResult(resultAnswerDto);
        } else {
            resultAnswerDto.setQuestionNotFound(true);
        }
        return resultAnswerDto;
    }

    // percent - [0.0 .. 1.0]
    private void setScoreToResult(ResultAnswerDto resultAnswerDto, double percent, int attemptsNumber){
        long score = Math.round(percent);
        resultAnswerDto.setScore(score);
        resultAnswerDto.setAttemptsNumber(attemptsNumber);
    }

    private void saveResult(ResultAnswerDto resultAnswerDto){
        UsverProgressLabWork usverProgressLabWork =
                usverProgressService.getUsverProgress(resultAnswerDto.getUsverId(), resultAnswerDto.getLabWorkId())
                        .orElseThrow(RuntimeException::new);
        usverProgressLabWork.getUsverProgressTasks()
                .stream()
                .filter(usverProgressTask -> usverProgressTask.getTask().getId().equals(resultAnswerDto.getTaskId()))
                .flatMap(usverProgressTask -> usverProgressTask.getUsverProgressQuestions().stream())
                .filter(usverProgressQuestion -> usverProgressQuestion.getQuestion().getId().equals(resultAnswerDto.getQuestionId()))
                .forEach(usverProgressQuestion -> {
                    usverProgressQuestion.setScore(resultAnswerDto.getScore());
                    usverProgressQuestion.setAttemptsNumber(resultAnswerDto.getAttemptsNumber());
                });
        usverProgressService.saveUsverProgress(usverProgressLabWork);
    }


    // return - [0.0 .. 1.0]
    private double testComputerSystemDto(ComputerSystemDto standart, ComputerSystemDto answer){
        boolean isIsomorfic = standart.getGraphSystem().isomorfic(answer.getGraphSystem());

        if (!isIsomorfic) {
            Set<ComputerSystemDto> standartSet = getManySubgraphsComputerSystem(standart);
            Set<ComputerSystemDto> answerSet = getManySubgraphsComputerSystem(answer);

            isIsomorfic = Utils.setsEquals(
                    standartSet,
                    answerSet,
                    (computerSystemDto1, computerSystemDto2) ->  computerSystemDto1.getGraphSystem().isomorfic(computerSystemDto2.getGraphSystem())
            );
        }

        return isIsomorfic ? 1 : 0;
    }

    private Set<ComputerSystemDto> getManySubgraphsComputerSystem(ComputerSystemDto computerSystemDto){
        Map<ComputerSystemElementType, List<SimpleComputerSystemElement>> elementTypeListMap = computerSystemDto.getSimpleGraphSystem().getElementSet()
                .stream()
                .filter(simpleComputerSystemElement -> simpleComputerSystemElement.getType() != ComputerSystemElementType.POINT)
                .collect(Collectors.groupingBy(SimpleComputerSystemElement::getType));
        Set<SimpleComputerSystemElement> pointSet = computerSystemDto.getSimpleGraphSystem().getElementSet()
                .stream()
                .filter(simpleComputerSystemElement -> simpleComputerSystemElement.getType() == ComputerSystemElementType.POINT)
                .collect(Collectors.toSet());

        Set<Duet<SimpleComputerSystemElement, SimpleComputerSystemElement>> cpuConnectors = computerSystemDto.getSimpleGraphSystem().getConnectionSet()
                .stream()
                .filter(duet -> duet.getA().getType() == ComputerSystemElementType.CPU || duet.getB().getType() == ComputerSystemElementType.CPU)
                .collect(Collectors.toSet());
        Set<Duet<SimpleComputerSystemElement, SimpleComputerSystemElement>> ramConnectors = computerSystemDto.getSimpleGraphSystem().getConnectionSet()
                .stream()
                .filter(duet -> duet.getA().getType() == ComputerSystemElementType.RAM || duet.getB().getType() == ComputerSystemElementType.RAM)
                .collect(Collectors.toSet());
        Set<Duet<SimpleComputerSystemElement, SimpleComputerSystemElement>> ioConnectors = computerSystemDto.getSimpleGraphSystem().getConnectionSet()
                .stream()
                .filter(duet -> duet.getA().getType() == ComputerSystemElementType.IO || duet.getB().getType() == ComputerSystemElementType.IO)
                .collect(Collectors.toSet());

        Set<SimpleComputerSystemElement> cpuPoints = pointSet
                .stream()
                .filter(simpleComputerSystemElement ->
                        cpuConnectors
                                .stream()
                                .anyMatch(duet -> duet.getA().getUuid().equals(simpleComputerSystemElement.getUuid()) || duet.getB().getUuid().equals(simpleComputerSystemElement.getUuid()))
                )
                .collect(Collectors.toSet());
        Set<SimpleComputerSystemElement> ramPoints = pointSet
                .stream()
                .filter(simpleComputerSystemElement ->
                        ramConnectors
                                .stream()
                                .anyMatch(duet -> duet.getA().getUuid().equals(simpleComputerSystemElement.getUuid()) || duet.getB().getUuid().equals(simpleComputerSystemElement.getUuid()))
                )
                .collect(Collectors.toSet());
        Set<SimpleComputerSystemElement> ioPoints = pointSet
                .stream()
                .filter(simpleComputerSystemElement ->
                        ioConnectors
                                .stream()
                                .anyMatch(duet -> duet.getA().getUuid().equals(simpleComputerSystemElement.getUuid()) || duet.getB().getUuid().equals(simpleComputerSystemElement.getUuid()))
                )
                .collect(Collectors.toSet());

        ComputerSystemDto computerSystemDtoForCpu = new ComputerSystemDto(new SimpleGraphSystem());
        computerSystemDtoForCpu.getSimpleGraphSystem().getElementSet().addAll(elementTypeListMap.get(ComputerSystemElementType.CPU));
        computerSystemDtoForCpu.getSimpleGraphSystem().getElementSet().addAll(cpuPoints);
        computerSystemDtoForCpu.getSimpleGraphSystem().getConnectionSet().addAll(cpuConnectors);

        ComputerSystemDto computerSystemDtoForRam = new ComputerSystemDto(new SimpleGraphSystem());
        computerSystemDtoForRam.getSimpleGraphSystem().getElementSet().addAll(elementTypeListMap.get(ComputerSystemElementType.RAM));
        computerSystemDtoForRam.getSimpleGraphSystem().getElementSet().addAll(ramPoints);
        computerSystemDtoForRam.getSimpleGraphSystem().getConnectionSet().addAll(ramConnectors);

        ComputerSystemDto computerSystemDtoForIo = new ComputerSystemDto(new SimpleGraphSystem());
        computerSystemDtoForIo.getSimpleGraphSystem().getElementSet().addAll(elementTypeListMap.get(ComputerSystemElementType.IO));
        computerSystemDtoForIo.getSimpleGraphSystem().getElementSet().addAll(ioPoints);
        computerSystemDtoForIo.getSimpleGraphSystem().getConnectionSet().addAll(ioConnectors);

        Set<ComputerSystemDto> result = new HashSet<>();
        result.add(computerSystemDtoForCpu);
        result.add(computerSystemDtoForRam);
        result.add(computerSystemDtoForIo);

        return result;
    }

    private double testFormulaDto(FormulaDto standart, FormulaDto answer){
        boolean isEquals =  standart.equals(answer);
        if (!isEquals) {
            isEquals = FormulaTester.test(standart.getLatexFormula(), answer.getLatexFormula());
        }
        return isEquals ? 1 : 0;
    }

    private double testTextDto(TextDto standart, TextDto answer){
        boolean isAnswerFound = false;
        for (String possibleAnswer : standart.getText().split(TextDto.ANSWER_SEPARATOR)) {
            if(StemmerPorterRU.stemSentence(answer.getText()).equals(StemmerPorterRU.stemSentence(possibleAnswer))){
                isAnswerFound = true;
                break;
            }
        }
        return isAnswerFound ? 1 : 0;
    }

    private double testNumberDto(NumberDto standart, NumberDto answer){
        if(answer.getNumber() == null){
            return 0;
        }
        double standartNumber = standart.getNumber();
        double answerNumber = answer.getNumber();
        int n = standart.getAccuracy() == null ? 0 : standart.getAccuracy();

        if (n <= 0){
            return (standartNumber == answerNumber) ? 1 : 0;
        } else {
            for (int i = 0; i < n; i++){
                standartNumber *= 10;
                answerNumber *= 10;
            }
            long standartResult = Math.round(standartNumber);
            long answerResult = Math.round(answerNumber);

            if((standartResult == answerResult) || (standartResult + 1 == answerResult) || (standartResult - 1 == answerResult)){
                return 1;
            } else {
                return 0;
            }
        }
    }

    private double testMatchingDto(MatchingDto standart, MatchingDto answer){
        boolean isEquals =  standart.equals(answer);
        return isEquals ? 1 : 0;
    }

    private double testCheckDto(SelectDto standart, SelectDto answer){
        //Если все чекбоксы отмечены, то возвращаем ноль
        if(answer.getOptions().stream().allMatch(Trio::getA)){
            return 0;
        }

        //Собираем все очки для подсчёта коэффициентов
        int allScore = standart.getOptions().stream().mapToInt(Trio::getC).sum();

        //Считаем коэффициент ответа
        return Utils.collectTwoList(
                standart.getOptions(),
                answer.getOptions(),
                (elem1, elem2) -> new Duet<>(
                        elem1.getA().equals(elem2.getA()), //отмечаем true правильные ответы
                        (double) elem1.getC() / allScore
                )
        ).stream()
                .filter(Duet::getA)
                .mapToDouble(Duet::getB)
                .sum();
    }

    private double testRadioDto(SelectDto standart, SelectDto answer){
        OptionalInt prepodScore = standart.getOptions()
                .stream()
                .mapToInt(Trio::getC)
                .max();
        OptionalInt studentScore = answer.getOptions()
                .stream()
                .filter(Trio::getA)
                .mapToInt(Trio::getC)
                .findAny();

        //Если все коеффициенты равны, то просто проверяем по equals
        if (prepodScore.isPresent()) {
            boolean allCoefficientsEquals = standart.getOptions()
                    .stream()
                    .mapToInt(Trio::getC)
                    .allMatch(coefficient -> prepodScore.getAsInt() == coefficient);
            if(allCoefficientsEquals){
                List<Boolean> prepodAnswer = standart.getOptions()
                        .stream()
                        .map(Trio::getA)
                        .collect(Collectors.toList());
                List<Boolean> usverAnswer = answer.getOptions()
                        .stream()
                        .map(Trio::getA)
                        .collect(Collectors.toList());
                return (prepodAnswer.equals(usverAnswer)) ? 1 : 0;
            }
        }

        if (prepodScore.isPresent() && studentScore.isPresent()){
            return (double) studentScore.getAsInt() / prepodScore.getAsInt();
        } else if (prepodScore.isPresent()){
            return 0;
        } else if (studentScore.isPresent()){
            return 1;
        } else {
            return 0;
        }
    }

    private double testSortingDto(SortingDto standart, SortingDto answer){
        boolean isEquals =  standart.equals(answer);
        return isEquals ? 1 : 0;
    }
}
