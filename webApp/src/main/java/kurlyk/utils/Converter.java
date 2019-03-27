package kurlyk.utils;

import kurlyk.models.*;
import kurlyk.transfer.*;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class Converter {

    public static TokenDto tokenConverter(Token token){
        return TokenDto
                .builder()
                .value(token.getValue())
                .userLogin(token.getUser().getLogin())
                .userRole(token.getUser().getRole())
                .userState(token.getUser().getState())
                .userFirstName(token.getUser().getFirstName())
                .userMiddleName(token.getUser().getMiddleName())
                .userSecondName(token.getUser().getSecondName())
                .userStudyGroup(token.getUser().getStudyGroup())
                .build();
    }
    public static Token tokenConverter(TokenDto tokenDto){
        return Token
                .builder()
                .value(tokenDto.getValue())
                .build();
    }


    public static UserDto userConverter(User user){
        return UserDto
                .builder()
                .id(user.getId())
                .login(user.getLogin())
                .password(user.getHashPassword())
                .role(user.getRole())
                .state(user.getState())
                .firstName(user.getFirstName())
                .middleName(user.getMiddleName())
                .secondName(user.getSecondName())
                .studyGroup(user.getStudyGroup())
                .build();
    }
    public static User userConverter(UserDto userDto){
        return User
                .builder()
                .id(userDto.getId())
                .login(userDto.getLogin())
                .hashPassword(userDto.getPassword())
                .role(userDto.getRole())
                .state(userDto.getState())
                .firstName(userDto.getFirstName())
                .middleName(userDto.getMiddleName())
                .secondName(userDto.getSecondName())
                .studyGroup(userDto.getStudyGroup())
                .build();
    }


    public static QuestionDto questionConverter(Question question) {
        return QuestionDto
                .builder()
                .id(question.getId())
                .name(question.getName())
                .labNumber(question.getLabNumber())
                .questionType(question.getQuestionType())
                .question(question.getQuestion())
                .answer(question.getAnswer())
                .build();
    }
    public static Question questionConverter(QuestionDto questionDto){
        return Question
                .builder()
                .id(questionDto.getId())
                .name(questionDto.getName())
                .labNumber(questionDto.getLabNumber())
                .questionType(questionDto.getQuestionType())
                .question(questionDto.getQuestion())
                .answer(questionDto.getAnswer())
                .build();
    }


    public static LabWorkDto labWorkConverter(LabWork labWork){
        return LabWorkDto
                .builder()
                .id(labWork.getId())
                .name(labWork.getName())
                .atemptsNumber(labWork.getAtemptsNumber())
                .build();
    }
    public static LabWork labWorkConverter(LabWorkDto labWorkDto){
        return LabWork
                .builder()
                .id(labWorkDto.getId())
                .name(labWorkDto.getName())
                .atemptsNumber(labWorkDto.getAtemptsNumber())
                .build();
    }


    public static TaskDto taskConverter(Task task){
        return TaskDto
                .builder()
                .id(task.getId())
                .name(task.getName())
                .score(task.getScore())
                .build();
    }
    public static Task taskConverter(TaskDto taskDto){
        return Task
                .builder()
                .id(taskDto.getId())
                .name(taskDto.getName())
                .score(taskDto.getScore())
                .build();
    }


    public static <IN, OUT> List<OUT> listToList(List<IN> inputList, Function<IN, OUT> converter) {
        List<OUT> ouputList= new ArrayList<>();
        inputList.forEach(in -> ouputList.add(converter.apply(in)));
        return ouputList;
    }
}
