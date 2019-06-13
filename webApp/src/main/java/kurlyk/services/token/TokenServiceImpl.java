package kurlyk.services.token;

import kurlyk.model.Token;
import kurlyk.repositories.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@Service
public class TokenServiceImpl implements TokenService {
    private Long retirementAgeInDays = 1L;

    @Autowired
    private TokenRepository tokenRepository;

    @Override
    public void saveAndDeleteOld(Token token) {
        tokenRepository.deleteAll(
                tokenRepository.findAllByUsver(token.getUsver())
                .stream()
                .filter(usverToken -> usverToken.getCreateDate().compareTo(LocalDateTime.now().minusDays(retirementAgeInDays)) < 1)
                .collect(Collectors.toList())
        );
        tokenRepository.save(token);
    }
}
