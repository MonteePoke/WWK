package kurlyk.communication;


import com.google.gson.reflect.TypeToken;
import kurlyk.transfer.LoginDto;
import kurlyk.transfer.TaskDto;
import kurlyk.transfer.TokenDto;
import kurlyk.transfer.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.ConnectException;
import java.util.ArrayList;
import java.util.List;

@Component
public class Communicator extends AbstractCommunicator{

    @Autowired
    private UserInfo userInfo;

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



    public void login(LoginDto loginDto) throws ConnectException, IOException {
        Type type = new TypeToken<TokenDto>(){}.getType();
        TokenDto tokenDto = postData(type, loginDto, "/login");
        userInfo.setTokenDto(tokenDto);
    }

    public List<UserDto> getAllUsers() throws ConnectException, IOException {
        Type type = new TypeToken<ArrayList<UserDto>>(){}.getType();
        return getData(type, "/users");
    }

    public void postTask(TaskDto taskDto) throws ConnectException, IOException {
        postData(null, taskDto, "/task");
    }

    public List<TaskDto> getLab(Integer labNumber) throws ConnectException, IOException {
        Type type = new TypeToken<ArrayList<TaskDto>>(){}.getType();
        return getData(type, "/lab" + "/" + labNumber.toString());
    }
}
