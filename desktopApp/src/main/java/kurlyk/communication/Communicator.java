package kurlyk.communication;


import com.google.gson.reflect.TypeToken;
import kurlyk.models.*;
import kurlyk.transfer.LoginDto;
import kurlyk.transfer.TokenDto;
import kurlyk.transfer.UserProgressDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.ConnectException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public void login(LoginDto loginDto) throws ConnectException, IOException {
        Type type = new TypeToken<TokenDto>(){}.getType();
        TokenDto tokenDto = postData(type, loginDto, "/login");
        userInfo.setTokenDto(tokenDto);
    }

    public User getUser() throws ConnectException, IOException {
        Type type = new TypeToken<User>(){}.getType();
        return getData(type, "/users");
    }

    public List<User> getUsers() throws ConnectException, IOException {
        Type type = new TypeToken<ArrayList<User>>(){}.getType();
        return getData(type, "/users");
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
        return getData(type, "/lab-works");
    }


    /*
        Task
     */
    public Task getTask(Long id) throws ConnectException, IOException {
        Type type = new TypeToken<Task>(){}.getType();
        return getData(type, "/task/" + id.toString());
    }

    public List<Task> getTasks(Long labWorkId) throws ConnectException, IOException {
        Type type = new TypeToken<ArrayList<Task>>(){}.getType();
        return getData(type, "/tasks/" + labWorkId.toString());
    }


    /*
        Question
     */
    public Question getQuestion(Long id) throws ConnectException, IOException {
        Type type = new TypeToken<Question>(){}.getType();
        return getData(type, "/question/" + id.toString());
    }

    public List<Question> getQuestions(Long taskId) throws ConnectException, IOException {
        Type type = new TypeToken<ArrayList<Question>>(){}.getType();
        return getData(type, "/questions/" + taskId.toString());
    }

    public void postQuestion(Question question) throws ConnectException, IOException {
        postData(null, question, "/question");
    }


    /*
        UserPrigress
     */
    public UserProgress getOneUserProgress(Long id) throws ConnectException, IOException {
        Type type = new TypeToken<UserProgress>(){}.getType();
        return getData(type, "/user/progress/" + id.toString());
    }

    public List<UserProgressDto> getUserProgress(UserProgressDto userProgressDto) throws ConnectException, IOException {
        Type type = new TypeToken<ArrayList<UserProgressDto>>(){}.getType();
        return getData(type, userProgressDtoToParameters(userProgressDto), "/user/progress");
    }

    public List<UserProgress> getFullUserProgress(UserProgressDto userProgressDto) throws ConnectException, IOException {
        Type type = new TypeToken<ArrayList<UserProgress>>(){}.getType();
        return getData(type, userProgressDtoToParameters(userProgressDto), "/user/full-progress");
    }

    public void postUserProgress(UserProgress userProgress) throws ConnectException, IOException {
        postData(null, userProgress, "/user/progress");
    }

    private Map<String, String> userProgressDtoToParameters(UserProgressDto userProgressDto){
        Map<String, String> parameters = new HashMap<>();
        parameters.put("userId", userProgressDto.getUserId() != null ? userProgressDto.getUserId().toString() : null);
        parameters.put("labWorkId", userProgressDto.getLabWorkId() != null ? userProgressDto.getLabWorkId().toString() : null);
        parameters.put("taskId", userProgressDto.getTaskId() != null ? userProgressDto.getTaskId().toString() : null);
        parameters.put("questionId", userProgressDto.getQuestionId() != null ? userProgressDto.getQuestionId().toString() : null);
        return parameters;
    }

}

