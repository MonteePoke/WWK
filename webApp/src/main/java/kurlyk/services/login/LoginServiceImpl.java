package kurlyk.services.login;

import kurlyk.models.Token;
import kurlyk.models.User;
import kurlyk.repositories.UsersRepository;
import kurlyk.services.token.TokenService;
import kurlyk.transfer.LoginDto;
import kurlyk.transfer.TokenDto;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;

@Component
public class LoginServiceImpl implements LoginService {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public TokenDto login(LoginDto loginDto) throws IllegalArgumentException {
        Optional<User> userCandidate = usersRepository.findOneByLogin(loginDto.getLogin());

        if (userCandidate.isPresent()) {
            User user = userCandidate.get();

            if (passwordEncoder.matches(loginDto.getPassword(), user.getPassword())) {
                Token token = Token.builder()
                        .user(user)
                        .value(RandomStringUtils.random(10, true, true))
                        .createDate(LocalDateTime.now())
                        .build();

                tokenService.saveAndDeleteOld(token);
                return TokenDto.fromToken(token);
            }
        } throw new IllegalArgumentException("User not found");
    }
}
