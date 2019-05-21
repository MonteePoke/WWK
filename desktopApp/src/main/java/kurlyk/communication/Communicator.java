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

    public void saveLabWork(LabWork labWork) throws ConnectException, IOException {
        postData(null, labWork, "/lab-work/");
    }

    public void deleteLabWork(LabWork labWork) throws ConnectException, IOException {
        getData(null, "/lab-work/delete/" + labWork.getId().toString());
    }

    public List<LabWorkTask> getLabWorkTaskMatching() throws ConnectException, IOException {
        Type type = new TypeToken<ArrayList<LabWorkTask>>(){}.getType();
        return getData(type, "/lab-work-task-matching/");
    }

    public List<LabWorkTask> getLabWorkTaskMatching(LabWork labWork) throws ConnectException, IOException {
        Type type = new TypeToken<ArrayList<LabWorkTask>>(){}.getType();
        return getData(type, "/lab-work-task-matching/" + labWork.getId().toString());
    }

    public void saveLabWorkTaskMatching(LabWorkTask labWorkTask) throws ConnectException, IOException {
        postData(null, labWorkTask, "/lab-work-task-matching/");
    }

    public void deleteLabWorkTaskMatching(LabWorkTask labWorkTask) throws ConnectException, IOException {
        getData(null, "/lab-work-task-matching/delete/" + labWorkTask.getId().toString());
    }

    public void deleteLabWorkTaskMatchingByLabWorkId(Long id) throws ConnectException, IOException {
        deleteData(null, id, "/lab-work-task-matching/lab-work/");
    }

    public void deleteLabWorkTaskMatchingByTaskId(Long id) throws ConnectException, IOException {
        deleteData(null, id, "/lab-work-task-matching/task/");
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

    public void saveTask(Task task) throws ConnectException, IOException {
        postData(null, task, "/task/");
    }

    public void deleteTask(Task task) throws ConnectException, IOException {
        getData(null, "/task/delete/" + task.getId().toString());
    }

    public List<TaskQuestion> getTaskQuestionMatching() throws ConnectException, IOException {
        Type type = new TypeToken<ArrayList<TaskQuestion>>(){}.getType();
        return getData(type, "/task-question-matching/");
    }

    public List<TaskQuestion> getTaskQuestionMatching(Task task) throws ConnectException, IOException {
        Type type = new TypeToken<ArrayList<TaskQuestion>>(){}.getType();
        return getData(type, "/task-question-matching/" + task.getId().toString());
    }

    public void saveTaskQuestionMatching(TaskQuestion taskQuestion) throws ConnectException, IOException {
        postData(null, taskQuestion, "/task-question-matching/");
    }

    public void deleteTaskQuestionMatching(TaskQuestion taskQuestion) throws ConnectException, IOException {
        getData(null, "/task-question-matching/delete/" + taskQuestion.getId().toString());
    }

    public void deleteTaskQuestionMatchingByTaskId(Long id) throws ConnectException, IOException {
        deleteData(null, id, "/task-question-matching/task/");
    }

    public void deleteTaskQuestionMatchingByQuestionId(Long id) throws ConnectException, IOException {
        deleteData(null, id, "/task-question-matching/question/");
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

    public void saveQuestion(Question question) throws ConnectException, IOException {
        postData(null, question, "/question/");
    }

    public void deleteQuestion(Question question) throws ConnectException, IOException {
        getData(null, "/question/delete/" + question.getId().toString());
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
    public UserProgress getOneUserProgress(Long id) throws ConnectException, IOException {
        Type type = new TypeToken<UserProgress>(){}.getType();
        return getData(type, "/user/progress/" + id.toString());
    }

    public List<UserProgressDto> getUserProgress(UserProgressDto userProgressDto) throws ConnectException, IOException {
        Type type = new TypeToken<ArrayList<UserProgressDto>>(){}.getType();
        return getData(type, userProgressDtoToParameters(userProgressDto), "/user/progress/");
    }

    public List<UserProgress> getFullUserProgress(UserProgressDto userProgressDto) throws ConnectException, IOException {
        Type type = new TypeToken<ArrayList<UserProgress>>(){}.getType();
        return getData(type, userProgressDtoToParameters(userProgressDto), "/user/full-progress/");
    }

    public void saveUserProgress(UserProgress userProgress) throws ConnectException, IOException {
        postData(null, userProgress, "/user/progress/");
    }

    private Map<String, String> userProgressDtoToParameters(UserProgressDto userProgressDto){
        Map<String, String> parameters = new HashMap<>();
        parameters.put("userId", userProgressDto.getUserId() != null ? userProgressDto.getUserId().toString() : null);
        parameters.put("labWorkId", userProgressDto.getLabWorkId() != null ? userProgressDto.getLabWorkId().toString() : null);
        parameters.put("taskId", userProgressDto.getTaskId() != null ? userProgressDto.getTaskId().toString() : null);
        parameters.put("questionId", userProgressDto.getQuestionId() != null ? userProgressDto.getQuestionId().toString() : null);
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

    public void saveSubject(Subject subject) throws ConnectException, IOException {
        postData(null, subject, "/subject/");
    }

    public void deleteSubject(Long id) throws ConnectException, IOException {
        deleteData(null, id, "/subject/");
    }
}

