package kurlyk.communication;


import com.google.gson.reflect.TypeToken;
import kurlyk.models.*;
import kurlyk.transfer.*;
import kurlyk.transfer.answer.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.ConnectException;
import java.util.*;

@Component
public class Communicator extends AbstractCommunicator{

    @Autowired
    private UsverInfo usverInfo;

    @Override
    public String getToken(){
        String token;
        try {
            token = usverInfo.getTokenDto().getValue();
            if (token == null){
                throw new NullPointerException();
            }
        } catch (NullPointerException e) {
            return "";
        }
        return token;
    }


    /*
        Usver
     */
    public boolean login(LoginDto loginDto) throws ConnectException, IOException {
        Type type = new TypeToken<TokenDto>(){}.getType();
        TokenDto tokenDto = postData(type, loginDto, "/login/");
        if(tokenDto.getValue().equals("")){
            return false;
        } else {
            usverInfo.setTokenDto(tokenDto);
            return true;
        }
    }

    public Usver getUser() throws ConnectException, IOException {
        Type type = new TypeToken<Usver>(){}.getType();
        return getData(type, "/usvers/");
    }

    public List<Usver> getUsers() throws ConnectException, IOException {
        Type type = new TypeToken<ArrayList<Usver>>(){}.getType();
        return getData(type, "/usvers/");
    }


    /*
        LabWork
     */
    public LabWork getLabWork(Long id) throws ConnectException, IOException {
        Type type = new TypeToken<LabWork>(){}.getType();
        return getData(type, "/lab-work/" + id.toString());
    }

    public List<LabWork> getLabWorks() throws ConnectException, IOException {
        Type type = new TypeToken<ArrayList<LabWork>>(){}.getType();
        return getData(type, "/lab-works/");
    }

    public Long saveLabWork(LabWork labWork) throws ConnectException, IOException {
        Type type = new TypeToken<Long>(){}.getType();
        return postData(type, labWork, "/lab-work/");
    }

    public void deleteLabWork(LabWork labWork) throws ConnectException, IOException {
        deleteData(null, labWork.getId(), "/lab-work/");
    }


    /*
        Task
     */
    public Task getTask(Long id) throws ConnectException, IOException {
        Type type = new TypeToken<Task>(){}.getType();
        return getData(type, "/task/" + id.toString());
    }

    public List<Task> getTasks(LabWork labWork) throws ConnectException, IOException {
        Type type = new TypeToken<ArrayList<Task>>(){}.getType();
        return getData(type, "/tasks/" + labWork.getId().toString());
    }

    public List<Task> getTasks() throws ConnectException, IOException {
        Type type = new TypeToken<ArrayList<Task>>(){}.getType();
        return getData(type, "/tasks/");
    }

    public Long saveTask(Task task) throws ConnectException, IOException {
        Type type = new TypeToken<Long>(){}.getType();
        return postData(type, task, "/task/");
    }

    public void deleteTask(Task task) throws ConnectException, IOException {
        deleteData(null, task.getId(), "/task/");
    }


    /*
        Question
     */
    public Question getQuestion(Long id) throws ConnectException, IOException {
        Type type = new TypeToken<Question>(){}.getType();
        return getData(type, "/question/" + id.toString());
    }

    public List<Question> getQuestions(Task task) throws ConnectException, IOException {
        Type type = new TypeToken<ArrayList<Question>>(){}.getType();
        return getData(type, "/questions/" + task.getId().toString());
    }

    public List<Question> getQuestionHeaders(Task task) throws ConnectException, IOException {
        Type type = new TypeToken<ArrayList<Question>>(){}.getType();
        return getData(type, "/question-headers/" + task.getId().toString());
    }

    public List<Question> getQuestionHeaders() throws ConnectException, IOException {
        Type type = new TypeToken<ArrayList<Question>>(){}.getType();
        return getData(type, "/questions-headers/");
    }

    public Long saveQuestion(Question question) throws ConnectException, IOException {
        Type type = new TypeToken<Long>(){}.getType();
        return postData(type, question, "/question/");
    }

    public void deleteQuestion(Question question) throws ConnectException, IOException {
        deleteData(null, question.getId(), "/question/");
    }

    public List<Question> getQuestions(Integer pageNumber, Integer contentSize) throws ConnectException, IOException {
        Type type = new TypeToken<ArrayList<Question>>(){}.getType();
        return getData(type, pageInfoToParameters(pageNumber, contentSize), "/questions/");
    }

    private Map<String, String> pageInfoToParameters(Integer pageNumber, Integer contentSize){
        Map<String, String> parameters = new HashMap<>();
        parameters.put("pageNumber", pageNumber != null ? pageNumber.toString() : null);
        parameters.put("contentSize", contentSize != null ? contentSize.toString() : null);
        return parameters;
    }


    /*
        UserProgress
     */
    public List<UsverProgressLabWork> getUsverProgress(UsverLabWorkDto usverLabWorkDto) throws ConnectException, IOException {
        Type type = new TypeToken<ArrayList<UsverProgressLabWork>>(){}.getType();
        return getData(type, usverLabWorkDtoToParameters(usverLabWorkDto), "/usver/progress/");
    }

    public Long saveUsverProgress(UsverProgressLabWork usverProgressLabWork) throws ConnectException, IOException {
        Type type = new TypeToken<Long>(){}.getType();
        return postData(type, usverProgressLabWork, "/usver/progress/");
    }

    public void deleteUsverProgress(UsverProgressLabWork usverProgressLabWork) throws ConnectException, IOException {
        deleteData(null, usverProgressLabWork.getId(), "/usver/progress/");
    }

    public Optional<UsverLabWorkAccess> getUsverLabWorkAccess(UsverLabWorkDto usverLabWorkDto) throws ConnectException, IOException {
        Type type = new TypeToken<Optional<UsverLabWorkAccess>>(){}.getType();
        return getData(type, usverLabWorkDtoToParameters(usverLabWorkDto), "/usver/access/");
    }

    public Long saveUsverLabWorkAccess(UsverLabWorkAccess usverLabWorkAccess) throws ConnectException, IOException {
        Type type = new TypeToken<Long>(){}.getType();
        return postData(type, usverLabWorkAccess, "/usver/access/");
    }

    public void deleteUsverLabWorkAccess(UsverLabWorkAccess usverLabWorkAccess) throws ConnectException, IOException {
        deleteData(null, usverLabWorkAccess.getId(), "/usver/access/");
    }

    private Map<String, String> usverLabWorkDtoToParameters(UsverLabWorkDto usverLabWorkDto){
        Map<String, String> parameters = new HashMap<>();
        parameters.put("usverId", usverLabWorkDto.getUsverId() != null ? usverLabWorkDto.getUsverId().toString() : null);
        parameters.put("labWorkId", usverLabWorkDto.getLabWorkId() != null ? usverLabWorkDto.getLabWorkId().toString() : null);
        return parameters;
    }


    /*
        Subject
    */
    public Subject getSubject(Long id) throws ConnectException, IOException {
        Type type = new TypeToken<Subject>(){}.getType();
        return getData(type, "/subjects/" + id.toString());
    }

    public List<Subject> getSubjects() throws ConnectException, IOException {
        Type type = new TypeToken<ArrayList<Subject>>(){}.getType();
        return getData(type, "/subject/");
    }

    public Long saveSubject(Subject subject) throws ConnectException, IOException {
        Type type = new TypeToken<Long>(){}.getType();
        return postData(type, subject, "/subject/");
    }

    public void deleteSubject(Long id) throws ConnectException, IOException {
        deleteData(null, id, "/subject/");
    }


    /*
        testAnswer
    */
    public ResultAnswerDto testComputerSystemAnswer(ComputerSystemAnswerDto dto) throws ConnectException, IOException {
        Type type = new TypeToken<ResultAnswerDto>(){}.getType();
        return postData(type, dto, "/test-computer-system/");
    }

    public ResultAnswerDto testFormulaAnswer(FormulaAnswerDto dto) throws ConnectException, IOException {
        Type type = new TypeToken<ResultAnswerDto>(){}.getType();
        return postData(type, dto, "/test-formula/");
    }

    public ResultAnswerDto testTextAnswer(TextAnswerDto dto) throws ConnectException, IOException {
        Type type = new TypeToken<ResultAnswerDto>(){}.getType();
        return postData(type, dto, "/test-text/");
    }

    public ResultAnswerDto testNumberAnswer(NumberAnswerDto dto) throws ConnectException, IOException {
        Type type = new TypeToken<ResultAnswerDto>(){}.getType();
        return postData(type, dto, "/test-number/");
    }

    public ResultAnswerDto testMatchingAnswer(MatchingAnswerDto dto) throws ConnectException, IOException {
        Type type = new TypeToken<ResultAnswerDto>(){}.getType();
        return postData(type, dto, "/test-matching/");
    }

    public ResultAnswerDto testCheckAnswer(SelectAnswerDto dto) throws ConnectException, IOException {
        Type type = new TypeToken<ResultAnswerDto>(){}.getType();
        return postData(type, dto, "/test-check/");
    }

    public ResultAnswerDto testRadioAnswer(SelectAnswerDto dto) throws ConnectException, IOException {
        Type type = new TypeToken<ResultAnswerDto>(){}.getType();
        return postData(type, dto, "/test-radio/");
    }

    public ResultAnswerDto testSortingAnswer(SortingAnswerDto dto) throws ConnectException, IOException {
        Type type = new TypeToken<ResultAnswerDto>(){}.getType();
        return postData(type, dto, "/test-sorting/");
    }


    /*
        executeLabWork
    */
    public QuestionIdsDto getQuestionsForExecute(Long labWorkId, Integer variant) throws ConnectException, IOException {
        Type type = new TypeToken<QuestionIdsDto>(){}.getType();
        return getData(type, labWorkIdAndVariantToParameters(labWorkId, variant), "/questions-for-execute/");
    }

    private Map<String, String> labWorkIdAndVariantToParameters(Long labWorkId, Integer variant){
        Map<String, String> parameters = new HashMap<>();
        parameters.put("labWorkId", labWorkId != null ? labWorkId.toString() : null);
        parameters.put("variant", variant != null ? variant.toString() : null);
        return parameters;
    }

    public Question getQuestionForExecute(Long id) throws ConnectException, IOException {
        Type type = new TypeToken<Question>(){}.getType();
        return getData(type, "/question-for-execute/" + id.toString());
    }
}

