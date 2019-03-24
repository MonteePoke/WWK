package kurlyk.services.user;

import kurlyk.models.User;
import kurlyk.repositories.UsersRepository;
import kurlyk.transfer.UserDto;
import kurlyk.utils.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsersServiceImpl implements UsersService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void signUp(UserDto userDto) {
        User user = User.builder()
                .firstName(userDto.getFirstName())
                .middleName(userDto.getMiddleName())
                .secondName(userDto.getSecondName())
                .studyGroup(userDto.getStudyGroup())
                .hashPassword(passwordEncoder.encode(userDto.getPassword()))
                .login(userDto.getLogin())
                .role(userDto.getRole())
                .state(userDto.getState())
                .build();
        usersRepository.save(user);
    }

    @Override
    public List<UserDto> findAll() {
        List<UserDto> userDtos = new ArrayList<>();
        usersRepository.findAll().forEach(user -> userDtos.add(Converter.userToUserDto(user)));
        return userDtos;
    }

    @Override
    public UserDto findOne(Long userId) {
        return Converter.userToUserDto(usersRepository.getOne(userId));
    }
}
