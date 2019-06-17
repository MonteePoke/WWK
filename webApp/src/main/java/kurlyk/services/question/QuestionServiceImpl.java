package kurlyk.services.question;

import com.google.gson.Gson;
import kurlyk.common.Trio;
import kurlyk.common.algorithm.graph.SimpleGraphSystem;
import kurlyk.model.Question;
import kurlyk.repositories.QuestionRepository;
import kurlyk.repositories.UsverProgressQuestionRepository;
import kurlyk.transfer.QuestionForTableDto;
import kurlyk.transfer.tasks.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private UsverProgressQuestionRepository usverProgressQuestionRepository;


    @Override
    public Optional<Question> getQuestion(Long id) {
        return questionRepository.findOneById(id);
    }

    @Override
    public Optional<Question> getQuestionForExecute(Long id){
        Optional<Question> optionalQuestion = questionRepository.findOneById(id);
        if(optionalQuestion.isPresent()){
            Question question = optionalQuestion.get();
            switch (question.getQuestionType()){
                case COMPUTER_SYSTEM:
                    question.setAnswer(new Gson().toJson(
                            shuffleComputerSystemDto(new Gson().fromJson(question.getAnswer(), ComputerSystemDto.class))
                    ));
                    break;
                case FORMULA:
                    question.setAnswer(new Gson().toJson(
                            shuffleFormulaDto(new Gson().fromJson(question.getAnswer(), FormulaDto.class))
                    ));
                    break;
                case TEXT:
                    question.setAnswer(new Gson().toJson(
                            shuffleTextDto(new Gson().fromJson(question.getAnswer(), TextDto.class))
                    ));
                    break;
                case NUMBER:
                    question.setAnswer(new Gson().toJson(
                            shuffleNumberDto(new Gson().fromJson(question.getAnswer(), NumberDto.class))
                    ));
                    break;
                case SORTING:
                    question.setAnswer(new Gson().toJson(
                            shuffleSortingDto(new Gson().fromJson(question.getAnswer(), SortingDto.class))
                    ));
                    break;
                case MATCHING:
                    question.setAnswer(new Gson().toJson(
                            shuffleMatchingDto(new Gson().fromJson(question.getAnswer(), MatchingDto.class))
                    ));
                    break;
                case CHECK:
                case RADIO:
                    question.setAnswer(new Gson().toJson(
                            shuffleSelectDto(new Gson().fromJson(question.getAnswer(), SelectDto.class))
                    ));
                    break;
            }
            return optionalQuestion;
        } else {
            return Optional.empty();
        }
    }

    @Override
    public List<Question> getQuestions(Long taskId){
        return questionRepository.findByTaskId(taskId);
    }

    @Override
    public List<Question> getQuestions(int pageNumber, int contentSize){
        return questionRepository.findAll(PageRequest.of(pageNumber, contentSize)).getContent();
    }

    @Override
    public List<Question> getQuestionHeaders(Long taskId){
        return questionRepository.getQuestionHeadersByTaskId(taskId);
    }

    @Override
    public List<Question> getQuestionIdAndNumbers(Long taskId){
        return questionRepository.getQuestionIdAndNumbers(taskId);
    }

    @Override
    public List<Question> getQuestionHeaders(){
        return questionRepository.getQuestionHeaders();
    }

    @Override
    public Long updateQuestionHeader(Question question){
        Question fullQuestion = getQuestion(question.getId()).orElseThrow(RuntimeException::new);
        fullQuestion.setNumber(question.getNumber());
        fullQuestion.setName(question.getName());
        fullQuestion.setAttemptsNumber(question.getAttemptsNumber());
        fullQuestion.setScore(question.getScore());
        fullQuestion.setNegativeScore(question.getNegativeScore());
        fullQuestion.setDecScore(question.getDecScore());
        fullQuestion.setSkipQuestion(question.getSkipQuestion());
        fullQuestion.setInterval(question.getInterval());
        return saveQuestion(fullQuestion);
    }

    @Override
    public Long saveQuestion(Question question){
        return questionRepository.save(question).getId();
    }

    @Override
    public void deleteQuestion(Long id) {
        questionRepository.deleteById(id);
    }

    @Override
    public List<QuestionForTableDto> getQuestionsForTable(Integer pageNumber, Integer contentSize){
        if(pageNumber == 0 || contentSize == 0){
            return questionRepository.findAllForTable();
        } else {
            return questionRepository.findAllForTable(PageRequest.of(pageNumber, contentSize)).getContent();
        }
    }


    private ComputerSystemDto shuffleComputerSystemDto(ComputerSystemDto dto){
        dto.setSimpleGraphSystem(new SimpleGraphSystem());
        return dto;
    }

    private FormulaDto shuffleFormulaDto(FormulaDto dto){
        dto.setLatexFormula("");
        return dto;
    }

    private TextDto shuffleTextDto(TextDto dto){
        dto.setText("");
        return dto;
    }

    private NumberDto shuffleNumberDto(NumberDto dto){
        dto.setAccuracy(0);
        dto.setNumber(null);
        return dto;
    }

    private SortingDto shuffleSortingDto(SortingDto dto){
        SortingDto sortingDto = new SortingDto(dto);
        Collections.shuffle(sortingDto.getItems());
        return sortingDto;
    }

    private MatchingDto shuffleMatchingDto(MatchingDto dto){
        MatchingDto newMatchingDto = new MatchingDto(dto);
        Collections.shuffle(newMatchingDto.getLeftPart());
        Collections.shuffle(newMatchingDto.getRightPart());
        return newMatchingDto;
    }

    private SelectDto shuffleSelectDto(SelectDto dto){
        dto.setOptions(
                dto.getOptions()
                        .stream()
                        .map(trio -> new Trio<>(false, trio.getB(), trio.getC()))
                        .collect(Collectors.toList())
        );
        return dto;
    }
}
