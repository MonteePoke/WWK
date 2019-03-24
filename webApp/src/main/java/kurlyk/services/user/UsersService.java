package kurlyk.services.user;

import kurlyk.transfer.UserDto;

import java.util.List;


public interface UsersService {
    void signUp(UserDto userDto);

    List<UserDto> findAll();

    UserDto findOne(Long userId);
}
