package kurlyk.communication;


import com.google.gson.reflect.TypeToken;
import kurlyk.model.*;
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
        if (tokenDto == null)
            throw new ConnectException("Couldn't login");
        if(tokenDto.getValue().equals("")){
            return false;
        } else {
            usverInfo.setTokenDto(tokenDto);
            return true;
        }
    }
    public boolean register(Usver user) throws ConnectException, IOException {
        Type type = new TypeToken<ArrayList<Usver>>(){}.getType();
        List<Usver> usvers = getData(type, "/usvers/");
        for (Usver usver : usvers) {
            if (usver.getLogin().equals(user.getLogin()))
                return false;
        }

        type = new TypeToken<TokenDto>(){}.getType();
        TokenDto tokenDto = postData(type, user, "/usvers/");

        return true;
    }

    public Usver getUsver(Long id) throws ConnectException, IOException {
        Type type = new TypeToken<Usver>(){}.getType();
        return getData(type, "/usvers/" + id.toString());
    }

    public List<Usver> getUsvers() throws ConnectException, IOException {
        Type type = new TypeToken<ArrayList<Usver>>(){}.getType();
        return getData(type, "/usvers/");
    }

    public Role getRole(String roleName) throws ConnectException, IOException {
        Type type = new TypeToken<Role>(){}.getType();
        return getData(type, "/role/" + roleName);
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
        Competence
    */
    public Competence getCompetence(Long id) throws ConnectException, IOException {
        Type type = new TypeToken<Competence>(){}.getType();
        return getData(type, "/competence/" + id.toString());
    }

    public List<Competence> getCompetences() throws ConnectException, IOException {
        Type type = new TypeToken<ArrayList<Competence>>(){}.getType();
        return getData(type, "/competences/");
    }

    public Long saveCompetence(Competence competence) throws ConnectException, IOException {
        Type type = new TypeToken<Long>(){}.getType();
        return postData(type, competence, "/competence/");
    }

    public void deleteCompetence(Long competenceId) throws ConnectException, IOException {
        deleteData(null, competenceId, "/competence/");
    }


    /*
        LabWorkCompetence
    */
    public List<LabWorkCompetence> getLabWorkCompetence(Long labWorkId) throws ConnectException, IOException {
        Type type = new TypeToken<ArrayList<LabWorkCompetence>>(){}.getType();
        return getData(type, "/lab-work-competences/" + labWorkId.toString());
    }

    public Long saveLabWorkCompetence(LabWorkCompetence labWorkCompetence) throws ConnectException, IOException {
        Type type = new TypeToken<Long>(){}.getType();
        return postData(type, labWorkCompetence, "/lab-work-competence/");
    }

    public void deleteLabWorkCompetence(Long labWorkCompetenceId) throws ConnectException, IOException {
        deleteData(null, labWorkCompetenceId, "/lab-work-competence/");
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

    public Long updateQuestionHeader(Question question) throws ConnectException, IOException {
        Type type = new TypeToken<Long>(){}.getType();
        return postData(type, question, "/update-question-header/");
    }

    public void deleteQuestion(Question question) throws ConnectException, IOException {
        deleteData(null, question.getId(), "/question/");
    }

    public List<Question> getQuestions(Integer pageNumber, Integer contentSize) throws ConnectException, IOException {
        Type type = new TypeToken<ArrayList<Question>>(){}.getType();
        return getData(type, pageInfoToParameters(pageNumber, contentSize), "/questions/");
    }

    public List<QuestionForTableDto> getQuestionsForTable(Integer pageNumber, Integer contentSize) throws ConnectException, IOException {
        Type type = new TypeToken<ArrayList<QuestionForTableDto>>(){}.getType();
        return getData(type, pageInfoToParameters(pageNumber, contentSize), "/questions-for-table/");
    }

    private Map<String, String> pageInfoToParameters(Integer pageNumber, Integer contentSize){
        Map<String, String> parameters = new HashMap<>();
        parameters.put("pageNumber", pageNumber != null ? pageNumber.toString() : "0");
        parameters.put("contentSize", contentSize != null ? contentSize.toString() : "0");
        return parameters;
    }


    /*
        UsverProgress
     */
    public Long saveUsverProgress(UsverProgressLabWork usverProgressLabWork) throws ConnectException, IOException {
        Type type = new TypeToken<Long>(){}.getType();
        return postData(type, usverProgressLabWork, "/usver/progress/");
    }

    public Long saveUsverProgressQuestion(UsverProgressQuestion usverProgressQuestion) throws ConnectException, IOException {
        Type type = new TypeToken<Long>(){}.getType();
        return postData(type, usverProgressQuestion, "/usver/progress-question/");
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
        parameters.put("usverId", usverLabWorkDto.getUsverId() != null ? usverLabWorkDto.getUsverId().toString() : "0");
        parameters.put("labWorkId", usverLabWorkDto.getLabWorkId() != null ? usverLabWorkDto.getLabWorkId().toString() : "0");
        return parameters;
    }

    public List<UsverProgressQuestion> getUsverProgressQuestions(Long labWorkId, Long usverId) throws ConnectException, IOException {
        Type type = new TypeToken<List<UsverProgressQuestion>>(){}.getType();
        return getData(type, usverIdAndlabWorkIdToParameters(usverId, labWorkId), "/usver/progress");
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
        return postData(type, dto, "/test/computer-system/");
    }

    public ResultAnswerDto testFormulaAnswer(FormulaAnswerDto dto) throws ConnectException, IOException {
        Type type = new TypeToken<ResultAnswerDto>(){}.getType();
        return postData(type, dto, "/test/formula/");
    }

    public ResultAnswerDto testTextAnswer(TextAnswerDto dto) throws ConnectException, IOException {
        Type type = new TypeToken<ResultAnswerDto>(){}.getType();
        return postData(type, dto, "/test/text/");
    }

    public ResultAnswerDto testNumberAnswer(NumberAnswerDto dto) throws ConnectException, IOException {
        Type type = new TypeToken<ResultAnswerDto>(){}.getType();
        return postData(type, dto, "/test/number/");
    }

    public ResultAnswerDto testMatchingAnswer(MatchingAnswerDto dto) throws ConnectException, IOException {
        Type type = new TypeToken<ResultAnswerDto>(){}.getType();
        return postData(type, dto, "/test/matching/");
    }

    public ResultAnswerDto testCheckAnswer(SelectAnswerDto dto) throws ConnectException, IOException {
        Type type = new TypeToken<ResultAnswerDto>(){}.getType();
        return postData(type, dto, "/test/check/");
    }

    public ResultAnswerDto testRadioAnswer(SelectAnswerDto dto) throws ConnectException, IOException {
        Type type = new TypeToken<ResultAnswerDto>(){}.getType();
        return postData(type, dto, "/test/radio/");
    }

    public ResultAnswerDto testSortingAnswer(SortingAnswerDto dto) throws ConnectException, IOException {
        Type type = new TypeToken<ResultAnswerDto>(){}.getType();
        return postData(type, dto, "/test/sorting/");
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
        parameters.put("labWorkId", labWorkId != null ? labWorkId.toString() : "0");
        parameters.put("variant", variant != null ? variant.toString() : "0");
        return parameters;
    }

    public Question getQuestionForExecute(Long id) throws ConnectException, IOException {
        Type type = new TypeToken<Question>(){}.getType();
        return getData(type, "/question-for-execute/" + id.toString());
    }

    /*
        statistic
    */
    public List<UsverProgressLabWork> getStatiscticByUsverId(Long usverId) throws ConnectException, IOException {
        Type type = new TypeToken<ArrayList<UsverProgressLabWork>>(){}.getType();
        return getData(type, usverIdToParameters(usverId), "/statistic-usver/");
    }

    public UsverProgressLabWork getStatiscticByUsverIdByLabWorkId(Long usverId, Long labWorkId) throws ConnectException, IOException {
        Type type = new TypeToken<UsverProgressLabWork>(){}.getType();
        return getData(type, usverIdAndlabWorkIdToParameters(usverId, labWorkId), "/statistic-usver-lab-work/");
    }

    private Map<String, String> usverIdToParameters(Long usverId){
        Map<String, String> parameters = new HashMap<>();
        parameters.put("usverId", usverId != null ? usverId.toString() : "0");
        return parameters;
    }

    private Map<String, String> usverIdAndlabWorkIdToParameters(Long usverId, Long labWorkId){
        Map<String, String> parameters = new HashMap<>();
        parameters.put("usverId", usverId != null ? usverId.toString() : "0");
        parameters.put("labWorkId", labWorkId != null ? labWorkId.toString() : "0");
        return parameters;
    }
}

