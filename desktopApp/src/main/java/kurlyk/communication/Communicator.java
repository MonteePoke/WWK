package kurlyk.communication;


import com.google.gson.reflect.TypeToken;
import kurlyk.models.*;
import kurlyk.transfer.LoginDto;
import kurlyk.transfer.ResultAnswer;
import kurlyk.transfer.TokenDto;
import kurlyk.transfer.UserLabWorkDto;
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
    private UserInfo userInfo;

    @Override
    public String getToken(){
        String token;
        try {
            token = userInfo.getTokenDto().getValue();
            if (token == null){
                throw new NullPointerException();
            }
        } catch (NullPointerException e) {
            return "";
        }
        return token;
    }


    /*
        User
     */
    public boolean login(LoginDto loginDto) throws ConnectException, IOException {
        Type type = new TypeToken<TokenDto>(){}.getType();
        TokenDto tokenDto = postData(type, loginDto, "/login/");
        if(tokenDto.getValue().equals("")){
            return false;
        } else {
            userInfo.setTokenDto(tokenDto);
            return true;
        }
    }

    public User getUser() throws ConnectException, IOException {
        Type type = new TypeToken<User>(){}.getType();
        return getData(type, "/users/");
    }

    public List<User> getUsers() throws ConnectException, IOException {
        Type type = new TypeToken<ArrayList<User>>(){}.getType();
        return getData(type, "/users/");
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
    public List<UserProgressLabWork> getUserProgress(UserLabWorkDto userLabWorkDto) throws ConnectException, IOException {
        Type type = new TypeToken<ArrayList<UserProgressLabWork>>(){}.getType();
        return getData(type, userLabWorkDtoToParameters(userLabWorkDto), "/user/progress/");
    }

    public Long saveUserProgress(UserProgressLabWork userProgressLabWork) throws ConnectException, IOException {
        Type type = new TypeToken<Long>(){}.getType();
        return postData(type, userProgressLabWork, "/user/progress/");
    }

    public void deleteUserProgress(UserProgressLabWork userProgressLabWork) throws ConnectException, IOException {
        deleteData(null, userProgressLabWork.getId(), "/user/progress/");
    }

    public Optional<UserLabWorkAccess> getUserLabWorkAccess(UserLabWorkDto userLabWorkDto) throws ConnectException, IOException {
        Type type = new TypeToken<Optional<UserLabWorkAccess>>(){}.getType();
        return getData(type, userLabWorkDtoToParameters(userLabWorkDto), "/user/access/");
    }

    public Long saveUserLabWorkAccess(UserLabWorkAccess userLabWorkAccess) throws ConnectException, IOException {
        Type type = new TypeToken<Long>(){}.getType();
        return postData(type, userLabWorkAccess, "/user/access/");
    }

    public void deleteUserLabWorkAccess(UserLabWorkAccess userLabWorkAccess) throws ConnectException, IOException {
        deleteData(null, userLabWorkAccess.getId(), "/user/access/");
    }

    private Map<String, String> userLabWorkDtoToParameters(UserLabWorkDto userLabWorkDto){
        Map<String, String> parameters = new HashMap<>();
        parameters.put("userId", userLabWorkDto.getUserId() != null ? userLabWorkDto.getUserId().toString() : null);
        parameters.put("labWorkId", userLabWorkDto.getLabWorkId() != null ? userLabWorkDto.getLabWorkId().toString() : null);
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
    public ResultAnswer testComputerSystemAnswer(ComputerSystemAnswerDto dto) throws ConnectException, IOException {
        Type type = new TypeToken<ResultAnswer>(){}.getType();
        return postData(type, dto, "/test-computer-system/");
    }

    public ResultAnswer testFormulaAnswer(FormulaAnswerDto dto) throws ConnectException, IOException {
        Type type = new TypeToken<ResultAnswer>(){}.getType();
        return postData(type, dto, "/test-formula/");
    }

    public ResultAnswer testTextAnswer(TextAnswerDto dto) throws ConnectException, IOException {
        Type type = new TypeToken<ResultAnswer>(){}.getType();
        return postData(type, dto, "/test-text/");
    }

    public ResultAnswer testNumberAnswer(NumberAnswerDto dto) throws ConnectException, IOException {
        Type type = new TypeToken<ResultAnswer>(){}.getType();
        return postData(type, dto, "/test-number/");
    }

    public ResultAnswer testMatchingAnswer(MatchingAnswerDto dto) throws ConnectException, IOException {
        Type type = new TypeToken<ResultAnswer>(){}.getType();
        return postData(type, dto, "/test-matching/");
    }

    public ResultAnswer testCheckAnswer(SelectAnswerDto dto) throws ConnectException, IOException {
        Type type = new TypeToken<ResultAnswer>(){}.getType();
        return postData(type, dto, "/test-check/");
    }

    public ResultAnswer testRadioAnswer(SelectAnswerDto dto) throws ConnectException, IOException {
        Type type = new TypeToken<ResultAnswer>(){}.getType();
        return postData(type, dto, "/test-radio/");
    }

    public ResultAnswer testSortingAnswer(SortingAnswerDto dto) throws ConnectException, IOException {
        Type type = new TypeToken<ResultAnswer>(){}.getType();
        return postData(type, dto, "/test-sorting/");
    }
}

