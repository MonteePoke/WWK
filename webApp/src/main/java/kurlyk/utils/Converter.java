package kurlyk.utils;

import kurlyk.models.Task;
import kurlyk.models.Token;
import kurlyk.models.User;
import kurlyk.transfer.TaskDto;
import kurlyk.transfer.TokenDto;
import kurlyk.transfer.UserDto;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class Converter {

    public static TokenDto tokenToTokenDto(Token token){
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

    public static UserDto userToUserDto(User user){
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

    public static TaskDto taskToTaskDto(Task task){
        return TaskDto
                .builder()
                .id(task.getId())
                .name(task.getName())
                .labNumber(task.getLabNumber())
                .labType(task.getLabType())
                .question(task.getQuestion())
                .answer(task.getAnswer())
                .build();
    }

    public static <IN, OUT> List<OUT> listToList(List<IN> inputList, Function<IN, OUT> converter) {
        List<OUT> ouputList= new ArrayList<OUT>();
        inputList.forEach(in -> ouputList.add(converter.apply(in)));
        return ouputList;
    }
}
