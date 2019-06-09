package kurlyk.services.login;

import kurlyk.models.Token;
import kurlyk.models.Usver;
import kurlyk.repositories.UsverRepository;
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
    private UsverRepository usverRepository;

    @Override
    public TokenDto login(LoginDto loginDto) throws IllegalArgumentException {
        Optional<Usver> usverCandidate = usverRepository.findOneByLogin(loginDto.getLogin());

        if (usverCandidate.isPresent()) {
            Usver usver = usverCandidate.get();

            if (passwordEncoder.matches(loginDto.getPassword(), usver.getPassword())) {
                Token token = Token.builder()
                        .usver(usver)
                        .value(RandomStringUtils.random(10, true, true))
                        .createDate(LocalDateTime.now())
                        .build();

                tokenService.saveAndDeleteOld(token);
                return TokenDto.fromToken(token);
            }
        } throw new IllegalArgumentException("Usver not found");
    }
}
